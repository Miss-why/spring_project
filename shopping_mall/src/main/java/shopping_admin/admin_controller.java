package shopping_admin;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class admin_controller {

	PrintWriter pw =null;
	
	//do파일로 jsp 파일 실행
	@RequestMapping("/admin/add_master.do")
	public ModelAndView add_master() {
		ModelAndView mv = new ModelAndView("./admin/add_master");
		return mv;
	}
	@RequestMapping("/admin/admin_list.do")
	public ModelAndView admin_list() {
		ModelAndView mv = new ModelAndView("./admin/amin_list");
		return mv;
	}
	@RequestMapping("/admin/index.do")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("./admin/index");
		return mv;
	}
	
	
	//패스워드 암호화(MD5)
	@Resource(name="md5pw")
	private md5 md;
	@Resource (name="adminlogin")
	private admin_loginok al;
	
	//로그인
	@PostMapping("/admin/admin_main.do")
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
