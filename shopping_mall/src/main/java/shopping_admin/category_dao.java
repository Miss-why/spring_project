package shopping_admin;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class category_dao {
	private int cidx, camidx;
	private String cls_code, clgmenu_code, clgmenu_name, use_ck;
	
	public ArrayList<Object> toList() {
		return null;
	}
}
