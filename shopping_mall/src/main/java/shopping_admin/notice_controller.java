package shopping_admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class notice_controller {

	@Resource(name = "noticemodule")
	private notice_module ntmd;
	
	final Integer DATA_COUNT_PER_PAGE = 15;
	final Integer LIMIT_PAGEING_COUNT = 10;
	PrintWriter pw = null;
	
	@GetMapping("/notice_list.do")
	public String notice_list(@SessionAttribute(required = false, name = "adminSessionData") ArrayList<Object> adata,
			@RequestParam(value = "", required = false) Integer page,
			HttpServletResponse res, Model m) throws Exception {
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
			m.addAttribute("dataCount", this.ntmd.notice_count());
			m.addAttribute("noticelist", this.ntmd.notice_listall(startNumber, DATA_COUNT_PER_PAGE));
		}
		return "notice_list";
	}
	
	@GetMapping("/notice_write.do")
	public String notice_write(@SessionAttribute(required = false, name = "adminSessionData") ArrayList<Object> adata,
			HttpServletResponse res, Model m) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (adata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('올바른 접근 방식이 아닙니다.'); location.href = './admin';</script>");
			this.pw.close();
		}
		else {
			m.addAttribute("loginIdx", adata.get(0));
			m.addAttribute("loginName", adata.get(3));
		}
		return "notice_write";
	}
	
	@GetMapping("/notice_view.do") // 미완성 - 글 페이지 추가 시 업데이트
	public String notice_view(@SessionAttribute(required = false, name = "adminSessionData") ArrayList<Object> adata,
			@RequestParam int nidx, HttpServletResponse res, Model m) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		if (adata == null) {
			this.pw = res.getWriter();
			this.pw.print("<script>alert('올바른 접근 방식이 아닙니다.'); location.href = './admin';</script>");
			this.pw.close();
		}
		else {
			this.ntmd.add_view_count(nidx);
			m.addAttribute("notidata", this.ntmd.notice_viewone(nidx));
		}
		return "notice_view";
	}
	
	@GetMapping("/notice_filedown.do")
	public void notice_filedown(@RequestParam(value = "", required = false) String oname, HttpServletRequest req,
			@RequestParam(value = "", required = false) String fname, HttpServletResponse res) throws Exception {
		if (fname.equals("") || fname == null || oname.equals("") || oname == null) {
			res.setContentType("text/html; charset=UTF-8");
			this.pw = res.getWriter();
			this.pw.print("<script>alert('올바른 접근 방식이 아닙니다.'); location.href = './admin';</script>");
			this.pw.close();
		}
		else {
			String url = req.getServletContext().getRealPath("/notice_file/");
			File f = new File(url + fname);
			// file 다운로드 설정
			res.setContentType("application/download");
			res.setContentLength((int)f.length());
			res.setHeader("Content-disposition", "attachment;filename=\"" + oname + "\"");
			// response 객체를 통해서 서버로부터 파일 다운로드
			OutputStream os = res.getOutputStream();
			// 파일 입력 객체 생성
			FileInputStream fis = new FileInputStream(f);
			FileCopyUtils.copy(fis, os);
			fis.close();
			os.close();
		}
	}
	
	@PostMapping("/notice_writeok.do")
	public void notice_writeok(@RequestParam("mfile") MultipartFile nfile, @ModelAttribute notice_dao dao,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		this.pw = res.getWriter();
		try {
			if (dao.getAlert_ck() == null) {
				dao.setAlert_ck("N");
			}
			dao.setNfile(new files_save().fsave(req, nfile, "/notice_file/"));
			int callback = this.ntmd.submit_notice(dao);
			if (callback > 0) {
				this.pw.print("<script>alert('공지사항이 정상적으로 등록되었습니다.'); location.href = '/notice_list.do';</script>");
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
	
	@PostMapping("/notice_delete.do")
	public void notice_delete(@RequestParam int del_ck[], HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.setContentType("text/html; charset=UTF-8");
		try {
			this.pw = res.getWriter();
			new files_delete(req, ntmd, del_ck);
			int callback = this.ntmd.del_notilist(del_ck);
			if (callback > 0) {
				this.pw.print("<script>alert('정상적으로 항목이 삭제되었습니다.'); location.href = './notice_list.do';</script>");
			}
			else {
				this.pw.print("<script>alert('데이터 오류가 발생하여 정상적으로 처리되지 않았습니다.'); history.go(-1);</script>");
			}
		} catch (Exception e) {
			System.out.println(e);
			this.pw.print("<script>alert('오류가 발생하여 정상적으로 처리되지 않았습니다.'); history.go(-1);</script>");
		} finally {
			this.pw.close();
		}
	}
}
