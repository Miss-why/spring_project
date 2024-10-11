var duplid_ck = "";

function idck_ajax() {
	var user_id = document.getElementById("admin_id");
	if (user_id.value == "") {
		alert ("아이디를 입력해주세요.");
		user_id.focus();
	}
	else {
		var html = new XMLHttpRequest();
		duplid_ck = "";
		
		html.onreadystatechange = function() {
			if (html.readyState == 4 && html.status == 200) {
				duplid_ck = this.response;
				if (duplid_ck == "ok") {
					if (confirm("사용 가능한 아이디입니다.\n이 아이디를 사용하시겠습니까?")) {
						user_id.readOnly = true;
					}
				}
				else {
					alert("사용 불가능한 아이디입니다.");
				}
			}
		}
		html.open("POST", "./adminid_check.do", true);
		html.send("adminid=" + user_id.value);
	}
}

function js_signsubmit() {
	var mtel1 = document.getElementById("mtel1").value;
	var mtel2 = document.getElementById("mtel2").value;
	var mtel3 = document.getElementById("mtel3").value;
	document.getElementById("mtelsum").value = mtel1 + mtel2 + mtel3;
	
	const cellPhone = /^(?:(010)|(01[1|6|7|8|9]))(\d{3,4})(\d{4})$/;
	const eMail = /[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]$/i;
	
	if (!admin_login_frm.amid.readOnly || admin_login_frm.amid.value == "") {
		alert("아이디 중복 체크를 진행해주세요.");
		admin_login_frm.amid.focus();
	}
	else if (admin_login_frm.ampass.value == "") {
		alert("패스워드를 입력해주세요.");
		admin_login_frm.ampass.focus();
	}
	else if (admin_login_frm.ampass.value != document.getElementById("ampassck").value) {
		alert("동일한 패스워드를 입력해주세요.");
		admin_login_frm.ampass.focus();
	}
	else if (admin_login_frm.amname.value == "") {
		alert("이름을 입력해주세요.");
		admin_login_frm.amname.focus();
	}
	else if (!eMail.test(admin_login_frm.amemail.value)) {
		alert("이메일을 입력해주세요.");
		admin_login_frm.amemail.focus();
	}
	else if (!cellPhone.test(admin_login_frm.amphone.value)) {
		alert("전화번호를 입력해주세요.");
		document.getElementById("mtel2").focus();
	}
	else {
		if (confirm("작성하신 정보로 회원가입 하시겠습니까?")) {
			admin_login_frm.method = "POST";
			admin_login_frm.action = "./admin_signup.do";
			admin_login_frm.submit();
		}
	}
}

function js_signcancel() {
	location.href = "./admin";
}