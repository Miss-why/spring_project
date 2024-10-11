package shopping_admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("noticemodule")
public class notice_module {

	Map<String, String> keycode = null;
	
	@Resource(name = "shoptemplate")
	private SqlSessionTemplate sstm;
	
	public int notice_count() {
		return this.sstm.selectOne("shopnotice.alldata_count");
	}
	
	public notice_dao notice_viewone(int nidx) {
		return this.sstm.selectOne("shopnotice.one_notice_select", nidx);
	}
	
	public List<notice_dao> notice_listall(Integer pno, Integer dno) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("pageno", String.valueOf(pno));
		this.keycode.put("datano", String.valueOf(dno));
		return this.sstm.selectList("shopnotice.all_notice_select", this.keycode);
	}
	
	public int submit_notice(notice_dao dao) {
		return this.sstm.insert("shopnotice.noti_write", dao);
	}
	
	public void add_view_count(int nidx) {
		this.sstm.update("shopnotice.add_views", nidx);
	}
	
	public List<String> file_select(int del_ck[]) {
		return this.sstm.selectList("shopnotice.notifile_select", this.arrToString(del_ck));
	}
	
	public int del_notilist(int del_ck[]) {
		return this.sstm.delete("shopnotice.delete_notice", this.arrToString(del_ck));
	}
	
	public String arrToString(int arr[]) {
		StringBuilder sb = new StringBuilder();
		int w = 0;
		while (w < arr.length) {
			if (w == 0) {
				sb.append(arr[w]);				
			}
			else {
				sb.append(","+arr[w]);
			}
			w++;
		}
		return sb.toString();
	}
}
