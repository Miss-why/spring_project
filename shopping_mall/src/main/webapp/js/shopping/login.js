function page_location(no) {
	if (no == 1) {
		location.href = "./agree";
	}
	else if (no == 2) {
		alert("아직 없는 페이지입니다.");
	}
	else if (no == 3) {
		alert("아직 없는 페이지입니다.");
	}
}

function user_login() {
	if (login_frm.user_id.value == "") {
		alert("아이디를 입력하세요.");
		login_frm.login_id.focus();
		return false;
	}
	else if (login_frm.user_pw.value == "") {
		alert("패스워드를 입력하세요.");
		login_frm.login_pw.focus();
		return false;
	}
	else {
		login_frm.method = "POST";
		login_frm.action = "./user_loginok.do";
		login_frm.submit();
	}
}

function non_login() {
	console.log("test");
	return false;
}