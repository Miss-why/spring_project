package admin;

import org.springframework.stereotype.Repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Repository("ajoin_dao")
public class admin_user_dao {
	int adix, ctn;
	String aid, apass, aname, aemail, atel, adept, aposition, ajoindate, authz;
}
