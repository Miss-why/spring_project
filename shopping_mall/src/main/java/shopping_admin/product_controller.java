package shopping_admin;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class product_controller {

	@Resource(name = "productmodule")
	private product_module pdmd;
	
	final Integer DATA_COUNT_PER_PAGE = 15;
	final Integer LIMIT_PAGEING_COUNT = 10;
	PrintWriter pw = null;
	
	@GetMapping("/product_list.do")
	public String product_list(@SessionAttribute(required = false, name = "adminSessionData") ArrayList<Object> adata,
			@RequestParam(value = "", required = false) String search_part,
			@RequestParam(value = "", required = false) String search_word,
			@RequestParam(value = "", required = false) Integer page, HttpServletResponse res, Model m) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (adata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('올바른 접근 방식이 아닙니다.'); location.href = './admin';</script>");
			this.pw.close();
		}
		else {
			page = (page == null) ? 1 : page;
			Integer pg_start = ((page - 1) / LIMIT_PAGEING_COUNT) * LIMIT_PAGEING_COUNT + 1;
			Integer pg_end = (((page - 1) / LIMIT_PAGEING_COUNT) + 1) * LIMIT_PAGEING_COUNT;
			Integer startNumber = (page - 1) * DATA_COUNT_PER_PAGE;
			m.addAttribute("page", page);
			m.addAttribute("startNumber", startNumber);
			m.addAttribute("maxcount", DATA_COUNT_PER_PAGE);
			m.addAttribute("pg_limit", LIMIT_PAGEING_COUNT);
			m.addAttribute("pg_start", pg_start);
			m.addAttribute("pg_end", pg_end);
			m.addAttribute("dataCount", this.pdmd.product_count());
			if (search_part == null || search_word == null || search_part.equals("") || search_word.equals("")) {
				m.addAttribute("search_ck", "no");
				m.addAttribute("productlist", this.pdmd.product_search(startNumber, DATA_COUNT_PER_PAGE));
			}
			else {
				m.addAttribute("search_ck", "ok");
				m.addAttribute("search_part", search_part);
				m.addAttribute("search_word", search_word);
				m.addAttribute("searchCount", this.pdmd.searchprod_count(search_part, search_word));
				m.addAttribute("productlist", this.pdmd.product_search(startNumber, DATA_COUNT_PER_PAGE, search_part, search_word));
			}
		}
		return "product_list";
	}
	
	@GetMapping("/cate_list.do")
	public String cate_list(@SessionAttribute(required = false, name = "adminSessionData") ArrayList<Object> adata,
			@RequestParam(value = "", required = false) String search_part,
			@RequestParam(value = "", required = false) String search_word,
			@RequestParam(value = "", required = false) Integer page, HttpServletResponse res, Model m) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (adata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('올바른 접근 방식이 아닙니다.'); location.href = './admin';</script>");
			this.pw.close();
		}
		else {
			page = (page == null) ? 1 : page;
			Integer pg_start = ((page - 1) / LIMIT_PAGEING_COUNT) * LIMIT_PAGEING_COUNT + 1;
			Integer pg_end = (((page - 1) / LIMIT_PAGEING_COUNT) + 1) * LIMIT_PAGEING_COUNT;
			Integer startNumber = (page - 1) * DATA_COUNT_PER_PAGE;
			m.addAttribute("page", page);
			m.addAttribute("startNumber", startNumber);
			m.addAttribute("maxcount", DATA_COUNT_PER_PAGE);
			m.addAttribute("pg_limit", LIMIT_PAGEING_COUNT);
			m.addAttribute("pg_start", pg_start);
			m.addAttribute("pg_end", pg_end);
			m.addAttribute("dataCount", this.pdmd.category_count());
			if (search_part == null || search_word == null || search_part.equals("") || search_word.equals("")) {
				m.addAttribute("search_ck", "no");
				m.addAttribute("categorylist", this.pdmd.category_search(startNumber, DATA_COUNT_PER_PAGE));
			}
			else {
				m.addAttribute("search_ck", "ok");
				m.addAttribute("search_part", search_part);
				m.addAttribute("search_word", search_word);
				m.addAttribute("searchCount", this.pdmd.searchcate_count(search_part, search_word));
				m.addAttribute("categorylist", this.pdmd.category_search(startNumber, DATA_COUNT_PER_PAGE, search_part, search_word));
			}
		}
		return "cate_list";
	}
	
	@GetMapping("/product_write.do")
	public String product_write(@SessionAttribute(required = false, name = "adminSessionData") ArrayList<Object> adata,
			HttpServletResponse res, Model m) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (adata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('올바른 접근 방식이 아닙니다.'); location.href = './admin';</script>");
			this.pw.close();
		}
		else {
			m.addAttribute("catelist", this.pdmd.catelist_search());
		}
		return "product_write";
	}
	
	@GetMapping("/cate_write.do")
	public String cate_write(@SessionAttribute(required = false, name = "adminSessionData") ArrayList<Object> adata,
			HttpServletResponse res, Model m) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (adata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('올바른 접근 방식이 아닙니다.'); location.href = './admin';</script>");
			this.pw.close();
		}
		else {
			m.addAttribute("menulist", this.pdmd.catemenu_search());
		}
		return "cate_write";
	}
	
	@PostMapping("checkbox_delete.do")
	public void checkbox_delete(@RequestParam int del_ck[], HttpServletRequest req, HttpServletResponse res, String page_ck) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		try {
			this.pw = res.getWriter();
			if (page_ck.equals("product")) {
				new files_delete(req, pdmd, del_ck);
			}
			int callback = this.pdmd.del_list_ckbox(del_ck, page_ck);
			if (callback > 0) {
				String pagename = page_ck.equals("category") ? "cate" : page_ck;
				this.pw.print("<script>alert('정상적으로 항목이 삭제되었습니다.'); location.href = './" + pagename + "_list.do';</script>");
			}
			else {
				this.pw.print("<script>alert('오류가 발생하여 정상적으로 처리되지 않았습니다.'); history.go(-1);</script>");
			}
		} catch (Exception e) {
			this.pw.print("<script>alert('상품이 있는 카테고리는 삭제할 수 없습니다.'); history.go(-1);</script>");
		} finally {
			this.pw.close();
		}
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/producode_check.do")
	public void producode_check(@RequestBody(required = false) String product_code,
			HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		try {
			int callback = this.pdmd.duplicate_codeck(product_code.split("=")[1]);
			if (callback == 0) {
				this.pw.print("ok");
			}
			else {
				this.pw.print("error");
			}
		} catch (Exception e) {
			this.pw.print("error");
		} finally {
			this.pw.close();
		}
	}
	
	@PostMapping("/product_insert.do")
	public void product_insert(@RequestParam("imgfile") MultipartFile files[], @ModelAttribute product_dao dao,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		try {
			dao.setPimages(new files_save().fsave(req, files, "/product_img/"));
			int callback = this.pdmd.write_product(dao);
			if (callback > 0) {
				this.pw.print("<script>alert('상품이 정상적으로 등록되었습니다.'); location.href = '/product_list.do';</script>");
			}
			else {
				this.pw.print("<script>alert('데이터 오류가 발생하여 정상적으로 처리되지 않았습니다.'); history.go(-1);</script>");
			}
		} catch (Exception e) {
			this.pw.print("<script>alert('오류가 발생하여 정상적으로 처리되지 않았습니다.'); history.go(-1);</script>");
		} finally {
			this.pw.close();
		}
	}
	
	@PostMapping("/cate_writeok.do")
	public void category_insert(@ModelAttribute category_dao dao, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		try {
			int callback = this.pdmd.write_category(dao);
			if (callback > 0) {
				this.pw.print("<script>alert('카테고리가 정상적으로 등록되었습니다.'); location.href = '/cate_list.do';</script>");
			}
			else {
				this.pw.print("<script>alert('데이터 오류가 발생하여 정상적으로 처리되지 않았습니다.'); history.go(-1);</script>");
			}
		} catch (Exception e) {
			this.pw.print("<script>alert('오류가 발생하여 정상적으로 처리되지 않았습니다.'); history.go(-1);</script>");
		} finally {
			this.pw.close();
		}
	}
}
