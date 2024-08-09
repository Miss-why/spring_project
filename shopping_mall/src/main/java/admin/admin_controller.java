package admin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
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
	
	//관리자 등록 리스트 메인 모듈
	@Resource (name="admin_listok")
	private alistok listm;
	
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
				int result = lm.login_admin(aid, apass); // 모듈에서 결과값 받는 코드
				if (result == -2) {
					this.pw.print("<script>" + "alert('가입완료 후 전산 담당자가 승인을 해야 로그인 할 수 있습니다. 아직 승인되지 않았습니다.');"
							+ "history.go(-1);" + "</script>");
				} else if (result == -1) {
					this.pw.print("<script>" + "alert('관리자 아이디 및 패스워드를 확인하세요!!');" + "history.go(-1);" + "</script>");
				} else if (result == 1) {
					if (aid.equals("master")) {
						this.pw.print("<script>" + "alert('최고관리자 로그인 완료되었습니다.');" + "location.href='./admin_list.do';"
								+ "</script>");
					} else {
						this.pw.print("<script>" + "alert('관리자 로그인 완료되었습니다.');"
								+ "location.href='./shop_member_list.do';" + "</script>");
					}
				}
			}
			catch (Exception e) {
				//System.out.println(e);
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
	
	

	//관리자 등록 리스트(master)
	@PostMapping("/admin/alistok.do")
	private String admin_list(int aidx, Model m, HttpServletResponse res) throws Exception{
		this.pw= res.getWriter();
		List<admin_user_dao> all = listm.admin_lis
		admin_user_dao dao=null;
		if(aidx != null) { 
			dao = listm.
			m.addAttribute("area1",dao.adix+"px");
			m.addAttribute("area2",dao.aname+"px");
			m.addAttribute("area3",dao.adix+"px");
			m.addAttribute("area4",dao.atel+"px");
			m.addAttribute("area5",dao.aemail+"px");
			m.addAttribute("area6",dao.adept+"px");
			m.addAttribute("area7",dao.aposition+"px");
			m.addAttribute("area8",dao.ajoindate+"px");
			m.addAttribute("area9",dao.authz+"px");
		}
		m.addAttribute("all",all);
		return null;
    }
}

