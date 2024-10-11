package shopping_admin;

import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

public class agree_txtfile {

	private String url = "";
	
	public agree_txtfile(HttpServletRequest req) throws Exception {
		this.url = req.getServletContext().getRealPath("/resources/");
	}
	
	public void update_txtfile(String filename, String ftext) throws Exception {
		Files.write(Paths.get(this.url + filename + "_agree.txt"), ftext.getBytes());
	}
	
	public String select_txtfile(String filename) throws Exception {
		byte result[] = Files.readAllBytes(Paths.get(this.url + filename + "_agree.txt"));
		return new String(result);
	}
}
