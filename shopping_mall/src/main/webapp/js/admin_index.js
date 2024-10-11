function js_gopage(no) {
	if (no == 1) {
		location.href = "./add_master.do";
	}
	else if (no == 2) {
		alert("아직 없는 페이지 입니다.");
		//location.href = "";
	}
}

function admin_login() {
	if (admin_login_frm.login_id.value == "") {
		alert("아이디를 입력하세요.");
		admin_login_frm.login_id.focus();
		return false;
	}
	else if (admin_login_frm.login_pw.value == "") {
		alert("패스워드를 입력하세요.");
		admin_login_frm.login_pw.focus();
		return false;
	}
	else {
		admin_login_frm.method = "POST";
		admin_login_frm.action = "./admin_loginok.do";
		admin_login_frm.submit();
	}
}