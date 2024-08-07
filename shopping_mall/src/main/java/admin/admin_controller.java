package admin;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class admin_controller {

	PrintWriter pw =null;
	
	//do파일로 jsp파일 실행
	@RequestMapping("/admin/add_master.do")
	public String add_master(Model m) {
		return "/add_master";
	}
	@RequestMapping("/admin/admin_list.do")
	public String admin_list() {
		return "/admin_list";
	}
	@RequestMapping("/admin/index.do")
	public String index() {
		return "/index";
	}
	@RequestMapping("/admin/admin_siteinfo.do")
	public String siteinfo() {
		return "/admin_siteinfo";
	}

	//패스워드 암호화(MD5)
	@Resource(name="md5pw")
	private md5 md;
	
	//관리자 로그인 모듈
	@Resource (name="admin_loginok")
	private aloginok lm;
	
	//관리자 회원가입 모듈
	@Resource(name="admin_joinok")
	private ajoinok jm;
	
	//DAO
	@Resource(name="ajoin_dao")
	private admin_user_dao aud;
	
    // 아이디 중복 체크
	@PostMapping("/admin/idcheck.do")
	public String idcheck(HttpServletResponse res, @RequestParam("aid") String aid) throws Exception {
		int result = jm.checkid(aid); // 모듈에서 결과값 받는 코드
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		try {
			if(result == 0) {
				this.pw.print("ok");}
			else {
				this.pw.print("no");
			}
		} catch (Exception e) {
			this.pw.print("error");
		}
		return null;
	}
	
	//관리자 회원가입
	@PostMapping("/admin/ajoinok.do")
	private ArrayList<Object> admin_join(admin_user_dao dao, HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw= res.getWriter();
		try {
			int callback = jm.admin_insert(dao);
			if (callback > 0) {
				this.pw.print(
						"<script>" 
						+ "alert('관리자로 정상 등록 되었습니다.');"
						+ "location.href='./index.do';" 
						+ "</script>");
		}
		}catch(Exception e) {
			this.pw.print("<script>"
					+ "alert('DB 오류로 인하여 관리자에 등록되지 않았습니다.');"
					+ "location.href='./add_master.do';"
					+ "</script>");
			System.out.println(e);
		}finally {
			this.pw.close();
		}
		return null;
	}
	
	
	//관리자 로그인
	@PostMapping("/admin/admin_main.do")
	private String admin_login(@RequestParam("aid") String aid, @RequestParam("apass") String apass, HttpServletResponse res) throws Exception{
			res.setContentType("text/html;charset=utf-8");
//System.out.println(aid); //jsp에서 넘어오는 친구들
//System.out.println(apass);
			this.pw = res.getWriter();
			try {
					String loginuserdata = lm.login_admin(aid, apass); // 모듈에서 결과값 받는 코드
					//System.out.println(loginuserdata);
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
	
}
