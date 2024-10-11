package shopping_admin;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class files_save {
	
	public String fsave(HttpServletRequest req, MultipartFile files, String foldername) throws Exception {
		MultipartFile farr[] = { files };
		return fsave(req, farr, foldername);
	}
	
	public String fsave(HttpServletRequest req, MultipartFile files[], String foldername) throws Exception {
		StringBuilder sb = new StringBuilder();
		String furl = req.getServletContext().getRealPath(foldername);
		int w = 0;
		do {
			if (files[w].getSize() > 0) {
				if (w != 0) {
					sb.append("|");
				}
				String filename = files[w].getOriginalFilename();
				String rename = this.rename_file(filename);
				sb.append(filename + "|" + rename);
				FileCopyUtils.copy(files[w].getBytes(), new File(furl + rename));
			}
			w++;
		} while (w < files.length);
		return sb.toString();
	}

	public String rename_file(String name) {
		Date day = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(day) + (int) Math.ceil(Math.random() * 1000) + name.substring(name.indexOf("."));
	}
}
