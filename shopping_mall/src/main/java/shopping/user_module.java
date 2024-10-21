package shopping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import shopping_admin.homepage_dao;

@Repository("usermodule")
public class user_module {

	Map<String, String> keycode = null;
	
	@Resource(name = "shoptemplate")
	private SqlSessionTemplate sstm;
	
	public homepage_dao getHomepageInfo() {
		return this.sstm.selectOne("shopadmin.search_hp_info");
	}
	
	public List<malluser_dao> ulist_select() {
		return this.sstm.selectList("shopuser.user_search");
	}
	
	public int loginck_update(String uidx, String ck) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("uidx", uidx);
		this.keycode.put("ck", ck);
		return this.sstm.update("shopuser.userlogin_ck_update", this.keycode);
	}
	
	public String duplicate_idck(String uid) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "1");
		this.keycode.put("uid", uid);
		malluser_dao dao = this.sstm.selectOne("shopuser.user_search", this.keycode);
		return (dao == null) ? "ok" : "error";
	}
	
	public int insert_user(malluser_dao dao) {
		dao.setLogin_status("정상");
		return this.sstm.insert("shopuser.muser_signup", dao);
	}
	
	public malluser_dao login_user(String id, String pw) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "2");
		this.keycode.put("uid", id);
		this.keycode.put("upass", pw);
		malluser_dao dao = this.sstm.selectOne("shopuser.user_search", this.keycode);
		return dao;
	}
}
