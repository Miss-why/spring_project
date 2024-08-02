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
	@Resource (name="adminlogin")
	private admin_loginok al;
	
    // 아이디 중복 체크
	@PostMapping("/admin/add_master.do")
	@ResponseBody
	public String checkId(@RequestParam("aid") String aid) {
		String result = "";
		try {
			result = jm.checkid(aid);
		} catch (Exception e) {
			e.printStackTrace(); // 예외 로그 기록
			result = "아이디 중복 체크 중 오류가 발생했습니다.";
		}
		return result;
	}
	
	//관리자 회원가입
	@Resource(name="admin_joinok")
	private ajoinok jm; //관리자 회원가입 module
	
	@Resource(name="ajoin_dao")
	private admin_user_dao aud;
	
	@PostMapping("/admin/ajoinok.do")
	private ArrayList<Object> admin_join(admin_user_dao dao, HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw= res.getWriter();
		try {
			int callback = jm.admin_insert(dao);
			if (callback > 0) {
				this.pw.print(
						"<script>" 
						+ "alert('정상적으로 등록 완료 되었습니다.');"
						+ "location.href='./index.do';" 
						+ "</script>");
		}
		}catch(Exception e) {
			this.pw.print("<script>"
					+ "alert('DB 오류로 인하여 등록되지 않았습니다.');"
					+ "location.href='./add_master.do';"
					+ "</script>");
		}finally {
			this.pw.close();
		}
		return null;
	}
	
	
	//로그인
	@PostMapping("/admin_main.do")
	private String admin_login(String aid, String apass, HttpServletResponse res) throws Exception{
			res.setContentType("text/html;charset=utf-8");
			this.pw = res.getWriter();
			try {
				if(aid=="" || apass=="") {
				this.pw.print("<script>"
						+"alert('관리자 아이디와 비밀번호를 모두 입력해주세요.');"
						+"history.go(-1);"
						+"</script>");
				}
				else {
					String encryptpw = md.md5_makeing(apass);
					//System.out.println(encryptpw);
					String loginuserdata = al.login_admin(aid, encryptpw);
					
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
	
}
