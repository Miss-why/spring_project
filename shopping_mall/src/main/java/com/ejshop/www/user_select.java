package com.ejshop.www;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//user table (select, insert, update, delete)하는 MODULE

@Repository("userselect")
public class user_select {
	@Resource(name="template")
	private SqlSessionTemplate tm;
	
	public ArrayList<Object> search_id(String uname, String uemail) { //1명의 정보만 가져올 것이라서 dao대신 arraylist 사용 (string으로 해도 됨 => 개발자 마음)
		
		ArrayList<Object> onedata = new ArrayList<Object>();
		Map<String, String> keycode = new HashMap<String, String>();
		keycode.put("part", "1");
		keycode.put("uname", uname);
		keycode.put("uemail", uemail);
		
		//mapper에서 작성한 select 문이 *로 들고오기 때문에 받는 것은 다 받아야해서 dao로 받음
		user_dao dao = tm.selectOne("Shopping_mall.search", keycode); //list 아니고 one!!
		System.out.println(dao.getUid());
		
		
		return onedata;
		
	}
}
