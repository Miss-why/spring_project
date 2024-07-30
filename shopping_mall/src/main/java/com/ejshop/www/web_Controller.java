package com.ejshop.www;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
/*public class web_Controller {*/ //[ md5 연결방법1 - resource]

// md5 : 회원가입, 로그인, 패그워드 변경, 1:1 문의, 자유게시판, 상품구매.... 등에서 사용
public class web_Controller extends md5_pass { //[md5 연결방법2 - extends]
	
	PrintWriter pw =null;
	
	
	/*
	★ 잊지마
	- DAO를 가져오려면 => @	ModelAttribute 사용 (단, 회원가입시 무조건 모델 어트리뷰트 당연히 써야함!!)
	- DAO를 안쓰고 정보 몇 개만 가져올 것이라고 판단되면, 자료형 객체 or @RequestParam을 이용해서 사용
	((@RequestParam => null으로 날라올때 조건처리가 간단한게 장점)
	*/
	
	@Resource(name = "userselect")
	private user_select us; //모듈 로드
	
	/*각각 id/pw 두개 맵핑 안나누고 하나로 해도 됨.*/
	@PostMapping("/idsearch.do")
	public String idsearch(String[] uname, String uemail, HttpServletResponse res) throws Exception{ //아이디 찾기
		res.setContentType("text/html;charset=utf-8");
		
		this.pw = res.getWriter();
		try {
			if(uname[0] == null || uemail==null) {//단, 자료형 객체로 받아서 사용시 조건문 필수적으로 걸어야 null일때 에러 안남!!
			this.pw.print("<script>"
					+"alert('올바른 접근 방식이 아닙니다.');"
					+"history.go(-1);"
					+"</script>");
			}
			else {
				ArrayList<Object> onedata = us.search_id(uname[0], uemail); //받는 것 arraylist로 받을 것임
			}
		}
		catch (Exception e) {
			System.out.println(e);
			this.pw.print("<script>"
					+"alert('Database 문제로 인하여 해당 정보가 확인되지 않습니다.');"
					+"history.go(-1);"
					+"</script>");
		}
		finally {
			this.pw.close();
		}
		return null;
	}
	
	@PostMapping("/passmodify.do")
	public String passmodify() { //패스워드 변경
		return null;
	}
	
	
	
	//@Resource(name="md5pass")
	//@ImportResource(name="md5pass")
	//private md5_pass md; //[방법1 - resource로 하는법]
	
	//패스워드 변경 여부 체크(MD5)
	@GetMapping("/passwd.do")
	public String passwd() {
		String pwd = "a1234";
		//String result = md.md5_makeing(pwd); // [방법1]
		String result = this.md5_makeing(pwd); //[방법2]
		System.out.println(result);
		return null;
	}
	
	@GetMapping("/restapi/do")
	//@SessionAttribut :  세션이 이미 등록 되어 있을 경우, 해당 정보를 가져올 수 있음
	public String restapi(@SessionAttribute(name="mid", required = false) String mid ) throws Exception {
		if(mid==null) {
			System.out.println("로그인해야만 결제내역을 확인하실 수 있습니다.");
		}
		else {
			System.out.println("결제내역은 다음과 같습니다.");
		}
		return null;
	}
	
	//HttpSession(interface) : 세션을 빠르게 구현하는 방식
	//빠르게 세션 만들때 쓰는 선생님 코드 스타일 => 이것 알고 써야함!! 막쓰면 안됨
	@PostMapping("loginok.do")
	public String loginok(@RequestParam(value = "", required = false) String mid, HttpSession session) {
		if(mid != null) {
		session.setAttribute("mid", mid);
		session.setMaxInactiveInterval(1800);
		}
		return null;
	}
	
	/*//정통방식
	@PostMapping("/loginok.do")
	public String loginok(String mid, HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.setAttribute("mid", mid);
		session.setMaxInactiveInterval(1800); //1800:30분 //이것 안넣으면 절단남!! (계속 로그아웃 됨)
		System.out.println(mid);
		return null;
	}
	*/
	
	
	//http://abc, http://www.abc 이런식으로 아무렇게나 접속할 수 있기때문에 크로스오리진쓴다.
	//@RequestBody : jSON.stringfy
	@CrossOrigin(origins = "*",allowedHeaders = "*")
	@PostMapping("/ajaxok2.do")//(value="/ajaxok2.do",consumes="application/json") 이거 안 먹힌다
	public String ajaxok2(@RequestBody String all_data,HttpServletResponse res)throws Exception {
		System.out.println(all_data);//{"all_data":[]}//{"all_data":["홍길동","김유신","이순신"]}이렇게 찍혀서 밖에서부터 풀어야된다. 데이터를 끄집어내야되니까.
		
		JSONObject jo = new JSONObject(all_data);//{}인식 시킨 후 key값으로 배열을 체크//그냥 json
		System.out.println(jo.get("all_data"));
		//[a,b,c] //new쓰면 [a,b,c]로 풀어놨던걸 다시 읽어서 못 풀은다. toString으로 써서 해도 된다.
		JSONArray ja = (JSONArray)jo.get("all_data");//그냥 json
		System.out.println(ja.get(0)); //데이터를 출력
		
		//Front가 dataType="json"=>JSON으로 생성하여 결과값을 회신
		JSONObject result = new JSONObject();
		result.put("result", "ok");
		this.pw = res.getWriter();
		this.pw.print(result);
		return null;
	
	}
	
	/*
	@RequestBody : GET/POST(X) JSON기반일 경우에만 사용한다.
	@ResponseBody : 미디어타입, 파라미터 타입 단, 인자값에 미선언함(ex. public @ResponseBody String ajaxok() 이렇게는 가능함)
	
	*/
	//ajax 통신 CORS 방식
	@CrossOrigin(origins="*",allowedHeaders = "*")
	
	
	//@RequestParam : 배열을 이용하여 대표키로 전달 또는 대표키 없이 보조키로 전달 될 경우 사용할 수 있음.
	@PostMapping("/ajaxok.do")//(value="/ajaxok.do",produces="application/json")//이게 중요한게 아니다. 이거 굳이 안 써도 된다.
	public String ajaxok(@RequestParam(value="all_data") List<String> alldata, //value값이 key이름이기때문에 프론트 data 대표키랑 맞춰야된다.
			HttpServletResponse res) throws Exception {
		System.out.println(alldata);
		System.out.println(alldata.get(0));
		this.pw=res.getWriter();
		JSONObject jo= new JSONObject(); //simple
		jo.put("result", "ok");
		this.pw.print(jo);
		this.pw.close();
		
		return null;
	}
	
}