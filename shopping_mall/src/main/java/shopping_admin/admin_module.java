package shopping_admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("adminmodule")
public class admin_module {

	Map<String, String> keycode = null;
	
	@Resource(name = "shoptemplate")
	private SqlSessionTemplate sstm;
	
	public String duplicate_idck(String id) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "1");
		this.keycode.put("amid", id);
		admin_dao dao = this.sstm.selectOne("shopadmin.search", this.keycode);
		return (dao == null) ? "ok" : "error";
	}
	
	public int admin_signup(admin_dao dao) {
		return this.sstm.insert("shopadmin.signup_insert", dao);
	}
	
	public admin_dao admin_login(String id, String pw) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "2");
		this.keycode.put("amid", id);
		this.keycode.put("ampass", pw);
		return this.sstm.selectOne("shopadmin.search", this.keycode);
	}
	
	public List<admin_dao> list_select() {
		return this.sstm.selectList("shopadmin.search");
	}
	
	public int loginck_update(String amidx, String ck) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("amidx", amidx);
		this.keycode.put("ck", ck);
		return this.sstm.update("shopadmin.admin_loginck", this.keycode);
	}
	
	public homepage_dao homepageinfo_select() {
		return this.sstm.selectOne("shopadmin.search_hp_info");
	}
	
	public int hpinfo_insert(homepage_dao dao) {
		return this.sstm.insert("shopadmin.insert_hp_info", dao);
	}
	
	public int hpinfo_update(homepage_dao dao) {
		return this.sstm.update("shopadmin.update_hp_info", dao);
	}
}
