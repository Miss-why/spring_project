
//관리자 로그인
function login_admin() {
	
	var aid = document.querySelector('input[name="aid"]').value;
    var apass = document.querySelector('input[name="apass"]').value;
	
    //빈칸 확인
	if(aid==""||apass==""){
		alert("관리자 아이디와 비밀번호를 모두 입력해주세요!");
	}
    //폼보내기
	else{
		alogin_frm.method = "post";
    	alogin_frm.action = "./admin_main.do";
    	alogin_frm.submit();
	}
}


//