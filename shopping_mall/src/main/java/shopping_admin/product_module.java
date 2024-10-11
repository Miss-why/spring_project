package shopping_admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("productmodule")
public class product_module {

	Map<String, String> keycode = null;
	
	@Resource(name = "shoptemplate")
	private SqlSessionTemplate sstm;
	
	public List<category_dao> menu_catelist() {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "1");
		return this.sstm.selectList("cateproduct.catelist_pwpage", this.keycode);
	}
	
	public List<product_dao> main_prolist() {
		return this.sstm.selectList("product_mainpage");
	}
	
	public List<category_dao> category_search(Integer pno, Integer dno) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("pageno", String.valueOf(pno));
		this.keycode.put("datano", String.valueOf(dno));
		return this.sstm.selectList("cateproduct.select_cateall", this.keycode);
	}
	
	public List<category_dao> category_search(Integer pno, Integer dno, String search_part, String search_word) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("pageno", String.valueOf(pno));
		this.keycode.put("datano", String.valueOf(dno));
		this.keycode.put("search_part", search_part);
		this.keycode.put("search_word", search_word);
		return this.sstm.selectList("cateproduct.select_catesearch", this.keycode);
	}
	
	public List<product_dao> product_search(Integer pno, Integer dno) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("pageno", String.valueOf(pno));
		this.keycode.put("datano", String.valueOf(dno));
		return this.sstm.selectList("cateproduct.select_prodall", this.keycode);
	}
	
	public List<product_dao> product_search(Integer pno, Integer dno, String search_part, String search_word) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("pageno", String.valueOf(pno));
		this.keycode.put("datano", String.valueOf(dno));
		this.keycode.put("search_part", search_part);
		this.keycode.put("search_word", search_word);
		return this.sstm.selectList("cateproduct.select_prodsearch", this.keycode);
	}
	
	public List<String> catemenu_search() {
		return this.sstm.selectList("cateproduct.select_category");
	}
	
	public int category_count() {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("table", "category");
		return this.sstm.selectOne("cateproduct.select_count", this.keycode);
	}
	
	public int searchcate_count(String search_part, String search_word) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("table", "category");
		this.keycode.put("part", "2");
		this.keycode.put("search_part", search_part);
		this.keycode.put("search_word", search_word);
		return this.sstm.selectOne("cateproduct.select_count", this.keycode);
	}
	
	public int product_count() {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("table", "product");
		return this.sstm.selectOne("cateproduct.select_count", this.keycode);
	}
	
	public int searchprod_count(String search_part, String search_word) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("table", "product");
		this.keycode.put("part", "2");
		this.keycode.put("search_part", search_part);
		this.keycode.put("search_word", search_word);
		return this.sstm.selectOne("cateproduct.select_count", this.keycode);
	}
	
	public int del_list_ckbox(int del_ck[], String page_ck) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("table", page_ck);
		this.keycode.put("column", page_ck.equals("product") ? "pidx" : "cidx");
		this.keycode.put("datalist", this.arrToString(del_ck));
		return this.sstm.delete("cateproduct.cateprod_ckbox", this.keycode);
	}
	
	public List<category_dao> catelist_search() {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "2");
		return this.sstm.selectList("cateproduct.catelist_pwpage", this.keycode);
	}
	
	public int duplicate_codeck(String code) {
		this.keycode = new HashMap<String, String>();
		this.keycode.put("part", "1");
		this.keycode.put("table", "product");
		this.keycode.put("pcode", code);
		return this.sstm.selectOne("cateproduct.select_count", this.keycode);
	}
	
	public int write_product(product_dao dao) {
		return this.sstm.insert("cateproduct.insert_product", dao);
	}
	
	public int write_category(category_dao dao) {
		return this.sstm.insert("cateproduct.insert_category", dao);
	}
	
	public List<String> img_select(int idx[]) {
		return this.sstm.selectList("cateproduct.select_file", this.arrToString(idx));
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
