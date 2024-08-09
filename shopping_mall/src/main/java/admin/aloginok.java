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
	public int login_admin(String aid, String apass) {
		Map<String, String> keycode = new HashMap<String, String>();
		String encryptpw = md.md5_makeing(apass); //사용자가 입력 비밀번호 암호화
		keycode.put("aid", aid);
		keycode.put("apass", encryptpw);
		
		//System.out.println(aid);
		//System.out.println(apass);
		//System.out.println(encryptpw); // => 얘가 일치하는 정보가 없으니까 db검색 못해서 nullpoint 뜸
		
		
		admin_user_dao dao = tm.selectOne("admin.admin_login", keycode); //사용자가 입력한 id& pw를 db에 넣어서 검색
		
		int result = 0;
		if(dao == null) {
			result = -1;
		}
		else {
			String dbid = dao.getAid(); // db에서 가져오는 id
			String dbpass = dao.getApass(); // db에서 가져오는 pw
			String authz = dao.getAuthz();
			if (dbid == null || dbpass == null || authz == null) {
				result = -1;
			} else if (dbid.equals(aid) && dbpass.equals(encryptpw) && authz.equals("승인")) {
				result = 1;
			} else if (dbid.equals(aid) && dbpass.equals(encryptpw) && authz.equals("미승인")) {
				result = -2;
			}
		}
		return result;

	}
	
	
}
