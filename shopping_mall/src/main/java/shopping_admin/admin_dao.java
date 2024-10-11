package shopping_admin;

import java.util.ArrayList;
import java.util.Collections;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class admin_dao {
	private int amidx, amcheck;
	private String amid, ampass, amname, amemail, amphone, amdepartment, amposition, amloginck, amindate;
	
	public ArrayList<Object> toList() {
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(this.getAmidx());
		al.add(this.getAmcheck());
		al.add(this.getAmid());
		al.add(this.getAmpass());
		al.add(this.getAmname());
		al.add(this.getAmemail());
		al.add(this.getAmphone());
		al.add(this.getAmdepartment());
		al.add(this.getAmposition());
		al.add(this.getAmloginck());
		al.add(this.getAmindate());
		al.removeAll(Collections.singletonList(null));
		return al;
	}
	
	public ArrayList<Object> toSessionList() {
		ArrayList<Object> al = new ArrayList<Object>();
		al.add(this.getAmidx());
		al.add(this.getAmcheck());
		al.add(this.getAmid());
		al.add(this.getAmname());
		al.add(this.getAmloginck());
		return al;
	}
}
