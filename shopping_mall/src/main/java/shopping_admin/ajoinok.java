package shopping_admin;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//관리자 회원가입 정보를 DB로 넘기는 Module
@Repository("admin_joinok")
public class ajoinok {

	@Resource(name="template")
	private SqlSessionTemplate tm;
	
	@Resource(name="md5pw")
	private md5 md;
	
	//DB에 회원가입에서 넘어온 DAO값 저장
	public int admin_insert(admin_user_dao dao) {
		
		
		//비밀번호 암호화
        String originalapass = dao.getApass(); //원래 비밀번호 가져오기
        String apasschange = md.md5_makeing(originalapass); //MD5 암호화
        dao.setApass(apasschange); //암호화된 비밀번호를 DAO에 설정.
        
        //핸드폰번호 합치기
        String originalatel = dao.getAtel();
        String atelsum = originalatel.replaceAll(",", "");
        dao.setAtel(atelsum);
        
        //아이디 중복체크
        
		
        //DB에 저장
		int result = tm.insert("Shopping_admin.admin_insert",dao);
		return result;
	}
	
}
