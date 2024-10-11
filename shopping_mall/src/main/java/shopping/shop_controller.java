package shopping;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import shopping_admin.password_sha3;
import shopping_admin.product_module;

@Controller
public class shop_controller extends password_sha3 {

	@Resource(name = "usermodule")
	private user_module usmd;
	
	@Resource(name = "productmodule")
	private product_module pdmd;
	
	PrintWriter pw = null;
	
	@GetMapping("/")
	public String shop_index(Model m) {
		m.addAttribute("catelist", this.pdmd.menu_catelist());
		m.addAttribute("productlist", this.pdmd.main_prolist());
		m.addAttribute("hpInfo", this.usmd.getHomepageInfo());
		return "shopping/index";
	}
	
	@GetMapping("/agree")
	public String shop_agree(Model m) {
		m.addAttribute("hpInfo", this.usmd.getHomepageInfo());
		return "shopping/agree";
	}
	@GetMapping("/login")
	public String shop_login(Model m) {
		m.addAttribute("hpInfo", this.usmd.getHomepageInfo());
		return "shopping/login";
	}
	
	@PostMapping("/join.do")
	public String shop_join(@RequestParam(name = "agreementInfoFl", required = false) String agreementInfoFl, Model m,
			@RequestParam(name = "privateApprovalFl", required = false) String privateApprovalFl, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (agreementInfoFl == null || privateApprovalFl == null || agreementInfoFl == "N" || privateApprovalFl == "N") {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('올바른 접근 방식이 아닙니다.'); location.href = './agree';</script>");
			this.pw.close();
		}
		else {
			m.addAttribute("hpInfo", this.usmd.getHomepageInfo());
			m.addAttribute("agreementInfoFl", agreementInfoFl);
			m.addAttribute("privateApprovalFl", privateApprovalFl);
		}
		return "shopping/join";
	}
	
	@GetMapping("/user_duplid.do")
	public void user_deplid(@RequestParam(value = "", required = false) String id, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		try {
			String callback = this.usmd.duplicate_idck(id);
			this.pw.print(callback);
		} catch (Exception e) {
			this.pw.print("error");
		} finally {
			this.pw.close();
		}
	}
	
	@PostMapping("/user_emailAuth.do")
	public void user_emailAuth(@RequestBody String requestData, HttpServletResponse res) throws Exception {
		this.pw = res.getWriter();
		JSONObject jo = new JSONObject(requestData);
		authemail_post ap = new authemail_post(jo.get("user_email").toString(), jo.get("security_code").toString());
		this.pw.print(ap.post_execute());
		this.pw.close();
	}
	
	@PostMapping("/user_signup.do")
	public void user_signup(@ModelAttribute malluser_dao dao, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		try {
			dao.setCkuse_email(dao.getCkuse_email() == null ? "N" : dao.getCkuse_email());
			dao.setCkuse_phone(dao.getCkuse_phone() == null ? "N" : dao.getCkuse_phone());
			dao.setUpass(this.encodePass(dao.getUpass()));
			int callback = this.usmd.insert_user(dao);
			if (callback > 0) {
				this.pw.print("<script>alert('회원가입이 성공적으로 완료되었습니다.'); location.href = './login';</script>");
			}
		} catch (Exception e) {
			this.pw.print("<script>alert('오류가 발생하여 회원가입이 실패하였습니다.'); history.go(-1);</script>");
		} finally {
			this.pw.close();
		}
	}
	
	@PostMapping("/user_loginok.do")
	public void user_loginok(String user_id, String user_pw, String save_uid, HttpServletResponse res, HttpSession htss) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		try {
			malluser_dao userdao = this.usmd.login_user(user_id, this.encodePass(user_pw));
			if (userdao.getLogin_status().equals("정상")) {
				//htss.setAttribute("userSessionData", userdao.toSessionList());
				//htss.setMaxInactiveInterval(1800);
				this.pw.print("<script>alert('로그인 성공하셨습니다.'); location.href = './admin_main.do';</script>");					
			}
			else {
				this.pw.print("<script>alert('해당 고객님은 현재 계정이 정지된 상황 입니다. 고객센터에 문의하세요.'); history.go(-1);</script>");
			}
		} catch (Exception e) {
			this.pw.print("<script>alert('아이디 및 패스워드를 확인해주세요.'); history.go(-1);</script>");
		} finally {
			this.pw.close();
		}
	}
	
	@GetMapping("/admin_main.do")
	public String admin_main(@SessionAttribute(required = false, name = "adminSessionData") ArrayList<Object> adata,
			HttpServletRequest req, HttpServletResponse res, Model m) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (adata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('올바른 접근 방식이 아닙니다.'); location.href = './admin';</script>");
			this.pw.close();
		}
		else {
			m.addAttribute("userlist", this.usmd.ulist_select());
		}
		return "shop_member_list";
	}
	
	@PostMapping("/userlogin_update.do")
	public void userlogin_update(String loginck, String valueck, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		try {
			int callback = this.usmd.loginck_update(loginck, valueck);
			if (callback > 0) {
				this.pw.print("<script>alert('정상적으로 변경하셨습니다.'); location.href = './admin_main.do';</script>");
			}
		} catch (Exception e) {
			this.pw.print("<script>alert('오류가 발생하여 정상적으로 변경되지 않았습니다.'); history.go(-1);</script>");
		} finally {
			this.pw.close();
		}
	}
}
