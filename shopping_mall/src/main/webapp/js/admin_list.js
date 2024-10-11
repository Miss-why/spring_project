var amloginck = document.getElementById("amloginck");
var amvalueck = document.getElementById("amvalueck");

function amlogin_ok(no) {
	if (confirm("정말로 해당 관리자의 등록을 승인하시겠습니까?")) {
		js_updateck(no, "Y");
	}
}

function amlogin_no(no) {
	if (confirm("정말로 해당 관리자의 승인을 비활성화 하시겠습니까?")) {
		js_updateck(no, "N");
	}
}

function js_updateck(no, ck) {
	amloginck.value = no;
	amvalueck.value = ck;
	amloginck_frm.method = "POST";
	amloginck_frm.action = "./update_loginck.do";
	amloginck_frm.submit();
}