package admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

//관리자 등록 리스트 module
@Repository("admin_listok")
public class alistok {

	@Resource(name = "template")
	private SqlSessionTemplate tm;
	
	//관리자 리스트 값을 가져오는 메소드
	public List<admin_user_dao> amemberlist() {
		List<admin_user_dao> all = new ArrayList<admin_user_dao>();
		all=tm.selectList("admin.admin_list");
		return all;
	}
	
	/*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
		Map<String, String> keycode = new HashMap<>();
        keycode.put("aid", aid);
        keycode.put("apass", encryptpw);
		
		//DAO에서 데이터 가져오기
        admin_user_dao dao = tm.selectOne("admin.admin_list", keycode);
        
        //데이터가 null이 아닐 때 request에 저장
        if (dao != null) {
            request.setAttribute("admindao", dao);
        } else {
            request.setAttribute("errormessage", "관리자 정보를 가져올 수 없습니다.");
        }
        
        //JSP로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_list.jsp");
        dispatcher.forward(request, response);
    }
    */
}
