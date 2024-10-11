package shopping;

import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.util.ResourceUtils;

public class authemail_post {
	private Properties prpt = new Properties();
	private String rscSession = "email_auth.properties";
	private String user_email = "";
	private String securitycode = "";
	private Session ss = null;
	private String subject = "[SHOP BAG] 회원 가입 이메일 인증 발송";
	private String mailtext = "본 메일은 Shop Bag 회원 가입을 위한 이메일 인증입니다.<br>"
			+ "아래의 인증번호를 입력한 후 회원가입을 클릭해주십시오.<br>"
			+ "이메일 인증번호 : ";
	
	public authemail_post(String em, String se) {
		this.user_email = em;
		this.securitycode = se;
	}
	public String post_execute() {
		String result = "";
		try {
			this.prpt.load(this.getClass().getClassLoader().getResourceAsStream(this.rscSession));
			String login_user = this.prpt.getProperty("login_user");
			String login_password = this.prpt.getProperty("login_password");
			this.ss = Session.getDefaultInstance(this.prpt, new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(login_user, login_password);
				}
			});
			MimeMessage msg = new MimeMessage(this.ss);
			msg.setFrom(new InternetAddress(this.prpt.getProperty("frommail"), this.prpt.getProperty("fromname")));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(this.user_email));
			msg.setSubject(this.subject);
			msg.setText(this.mailtext + this.securitycode);
			Transport.send(msg);
			result = "ok";
		} catch (Exception e) {
			result = "error";
		}
		return result;
	}
}
