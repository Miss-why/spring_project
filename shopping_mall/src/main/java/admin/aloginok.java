package admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//관리자 로그인 module
@Repository("admin_loginok")
public class aloginok {

	@Resource(name = "template")
	private SqlSessionTemplate tm;
	
	@Resource(name="md5pw")
	private md5 md;
	
	//관리자 로그인
	public String login_admin(String aid, String apass) {
		Map<String, String> keycode = new HashMap<String, String>();
		String encryptpw = md.md5_makeing(apass); //사용자가 입력 비밀번호 암호화
		keycode.put("aid", aid);
		keycode.put("apass", encryptpw);
		
		admin_user_dao dao = tm.selectOne("admin.admin_login", keycode); //사용자가 입력한 id& pw를 db에 넣어서 검색
		String dbid = dao.getAid(); //db에서 가져오는 id
		String dbpass = dao.getApass(); //db에서 가져오는 pw
		String authz = dao.getAuthz();

		String result = "";
		if(dbid.equals(aid) && dbpass.equals(encryptpw) && authz.equals("승인")) {
			result = "관리자 로그인 완료되었습니다.";
		}
		else if(dbid.equals(aid) && dbpass.equals(encryptpw) && authz.equals("미승인")) {
			result = "가입완료 후 전산 담당자가 승인을 해야 로그인 할 수 있습니다. 아직 미승인 "; 
		}
		else {
			result = "관리자 아이디 및 패스워드를 확인하세요!!";
		}
		System.out.println();
		return result;

	}
	
	
}
