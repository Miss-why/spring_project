package shopping;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class malluser_dao {
	private int uidx;
	private String uid, upass, uname, uemail, uphone, ckagree_use, ckagree_info;
	private String ckuse_email, ckuse_phone, login_status, uindate;
	
	public ArrayList<Object> toSessionList() {
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(this.getUidx());
		al.add(this.getUid());
		al.add(this.getUname());
		al.add(this.getLogin_status());
		return al;
	}
}
