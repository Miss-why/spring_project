package shopping_admin;

import java.security.MessageDigest;

import org.springframework.stereotype.Repository;

@Repository("md5pw")
public class md5 {
	
	public String md5_makeing(String apass) {
		StringBuilder sb = new StringBuilder();
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(apass.getBytes());
			for(byte bt : md.digest()) {
				sb.append(String.format("%x", bt));
			}
			
		}catch (Exception e) {
			sb.append("인자값 오류 발생으로 비밀번호가 생성되지 않았습니다.");
		}
		
		return sb.toString();
	}
}
