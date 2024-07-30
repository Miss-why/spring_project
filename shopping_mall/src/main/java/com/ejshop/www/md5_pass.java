package com.ejshop.www;

import java.security.MessageDigest;

import org.springframework.stereotype.Repository;


//패스워드를 md5형태로 변환하는 메소드
@Repository("md5pass") //여기에 꼭 repository 넣어야 패스워드 로드 가능함!!
public class md5_pass { //[방법1 - resource를 사용해서 하는 법]

//abstract class md5_pass extends user_dao { //이렇게 연결하면 md5_pass 불러올때 user_dao도 같이 불러오는 것임
//abstract class md5_pass { // [방법2 - 상속으로 해결하기]
	
	public String md5_makeing(String upass) {
		StringBuilder sb = new StringBuilder(); //return에 사용되는 객체
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			md.update(upass.getBytes());
			for(byte bt : md.digest()) {
				sb.append(String.format("%x", bt));
			}
			
		}catch (Exception e) {
			sb.append("인자값 오류 발생으로 비밀번호가 생성되지 않았습니다.");
		}
		
		return sb.toString();
	}
}
