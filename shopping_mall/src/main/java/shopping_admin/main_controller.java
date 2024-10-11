package shopping_admin;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class main_controller extends password_sha3 {

	@Resource(name = "adminmodule")
	private admin_module admd;
	
	PrintWriter pw = null;
	
	@GetMapping("/admin")
	public String adminmain() {
		return "admin_index";
	}
	
	@GetMapping("/add_master.do")
	public String add_master() {
		return "add_master";
	}
	
	@PostMapping("/agree_update.do")
	public void agree_update(@RequestParam(defaultValue = "", required = false) String agree_ck, HttpServletRequest req,
			@RequestParam(defaultValue = "", required = false) String agree_use, HttpServletResponse res,
			@RequestParam(defaultValue = "", required = false) String agree_info) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		try {
			new agree_txtfile(req).update_txtfile(agree_ck, agree_ck.equals("use") ? agree_use : agree_info);
			this.pw.print("<script>alert('성공적으로 약관 내용이 변경되었습니다.'); location.href = './admin_main.do';</script>");
		} catch (Exception e) {
			this.pw.print("<script>alert('오류가 발생하여 약관 수정에 실패하셨습니다.'); history.go(-1);</script>");
		} finally {
			this.pw.close();
		}
	}
	
	@GetMapping("/get_agree.do")
	public void get_agree(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		try {
			JSONObject jo = new JSONObject();
			agree_txtfile at = new agree_txtfile(req);
			jo.put("useAgree", at.select_txtfile("use"));
			jo.put("infoAgree", at.select_txtfile("info"));
			this.pw.print(jo);
		} catch (Exception e) {
			this.pw.print("오류가 발생하여 로드하지 못하였습니다.");
		} finally {
			this.pw.close();
		}
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/adminid_check.do")
	public void adminid_check(@RequestBody(required = false) String adminid, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		try {
			String callback = this.admd.duplicate_idck(adminid.split("=")[1]);
			this.pw.print(callback);
		} catch (Exception e) {
			this.pw.print("error");
		} finally {
			this.pw.close();
		}
	}
	
	@PostMapping("/admin_signup.do")
	public void admin_signup(@ModelAttribute admin_dao dao, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		try {
			dao.setAmpass(this.encodePass(dao.getAmpass()));
			int callback = this.admd.admin_signup(dao);
			if (callback > 0) {
				this.pw.print("<script>alert('회원가입이 성공적으로 완료되었습니다.\n"
						+ "최고 관리자의 승인 이후 로그인이 가능합니다.'); location.href = './admin';</script>");
			}
		} catch (Exception e) {
			this.pw.print("<script>alert('오류가 발생하여 회원가입이 실패하였습니다.'); history.go(-1);</script>");
		} finally {
			this.pw.close();
		}
	}
	
	@PostMapping("/admin_loginok.do")
	public void admin_loginok(String login_id, String login_pw,
			HttpServletResponse res, HttpSession htss) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		try {
			admin_dao admindao = this.admd.admin_login(login_id, this.encodePass(login_pw));
			if (admindao.getAmloginck().equals("Y")) {
				htss.setAttribute("adminSessionData", admindao.toSessionList());
				htss.setMaxInactiveInterval(1800);
				this.pw.print("<script>alert('로그인 성공하셨습니다.'); location.href = './admin_main.do';</script>");					
			}
			else {
				this.pw.print("<script>alert('관리자의 승인 이후 로그인할 수 있습니다.'); history.go(-1);</script>");
			}
		} catch (Exception e) {
			this.pw.print("<script>alert('아이디 및 패스워드를 확인해주세요.'); history.go(-1);</script>");
		} finally {
			this.pw.close();
		}
	}
	
	@GetMapping("/admin_logout.do")
	public void admin_logout(HttpSession htss, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		try {
			htss.invalidate();
			this.pw.print("<script>alert('로그아웃 하셨습니다.'); location.href = './admin';</script>");
		} catch (Exception e) {
			this.pw.print("<script>alert('올바른 접근 방식이 아닙니다.'); history.go(-1);</script>");
		} finally {
			this.pw.close();
		}
	}
	
	@GetMapping("/admin_list.do")
	public String admin_list(@SessionAttribute(required = false, name = "adminSessionData") ArrayList<Object> adata,
			HttpServletResponse res, Model m) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (adata == null || !String.valueOf(adata.get(1)).equals("1")) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('올바른 접근 방식이 아닙니다.'); location.href = './admin';</script>");
			this.pw.close();
		}
		else {
			m.addAttribute("adminlist", this.admd.list_select());
		}
		return "admin_list";
	}
	
	@GetMapping("/admin_siteinfo.do")
	public String admin_siteinfo(@SessionAttribute(required = false, name = "adminSessionData") ArrayList<Object> adata,
			HttpServletResponse res, Model m) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (adata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('올바른 접근 방식이 아닙니다.'); location.href = './admin';</script>");
			this.pw.close();
		}
		else {
			try {
				m.addAttribute("homepagedata", this.admd.homepageinfo_select().toList());				
			} catch (Exception e) {
				m.addAttribute("homepagedata", new homepage_dao().toList());
			}
		}
		return "admin_siteinfo";
	}
	
	@PostMapping("/hpinfo_update.do")
	public void hpinfo_update(@ModelAttribute homepage_dao dao, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		try {
			this.pw = res.getWriter();
			int callback = (dao.getHomekey() == 1) ? this.admd.hpinfo_update(dao) : this.admd.hpinfo_insert(dao);
			if (callback > 0) {
				this.pw.print("<script>alert('작성하신 설정이 정상적으로 저장되었습니다.'); location.href = './admin_siteinfo.do';</script>");
			}
		} catch (Exception e) {
			this.pw.print("<script>alert('오류가 발생하여 저장되지 않았습니다.'); history.go(-1);</script>");
		} finally {
			this.pw.close();
		}
	}
	
	@PostMapping("/update_loginck.do")
	public void update_loginck(String amloginck, String amvalueck, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		try {
			int callback = this.admd.loginck_update(amloginck, amvalueck);
			if (callback > 0) {
				this.pw.print("<script>alert('정상적으로 변경하셨습니다.'); location.href = './admin_list.do';</script>");
			}
		} catch (Exception e) {
			this.pw.print("<script>alert('오류가 발생하여 정상적으로 변경되지 않았습니다.'); history.go(-1);</script>");
		} finally {
			this.pw.close();
		}
	}
}
