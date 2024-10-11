package shopping_admin;

import java.security.MessageDigest;

public class password_sha3 {

	public String encodePass(String pw) throws Exception {
		StringBuilder sb = new StringBuilder();
		MessageDigest md = MessageDigest.getInstance("SHA3-256");
		md.update(pw.getBytes());
		for (byte bt : md.digest()) {
			sb.append(String.format("%X", bt));
		}
		return sb.toString();
	}
}
