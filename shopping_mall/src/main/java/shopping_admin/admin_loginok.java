package shopping_admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

// login id 및 pw 확인 module
@Repository("adminlogin")
public class admin_loginok {

	@Resource(name = "template")
	private SqlSessionTemplate tm;
	
	
	public String login_admin(String aid, String encryptpw) {
		Map<String, String> keycode = new HashMap<String, String>();
		keycode.put("part", "admin");
		keycode.put("aid", aid);
		keycode.put("apass", encryptpw);
		System.out.println(encryptpw);
		
		String result = "";
		admin_user_dao dao = tm.selectOne("Shopping_admin.admin_login", keycode);
		String dbid = dao.getAid();
		String dbpass = dao.getApass();
		if(dbid.equals(aid) && dbpass.equals(encryptpw)) {
			result = "관리자 페이지 로그인 되셨습니다.";
		}
		else {
			result = "관리자 아이디 및 패스워드를 확인하세요!!";
		}
		System.out.println(result);
		return result;

	}
	
	
}
