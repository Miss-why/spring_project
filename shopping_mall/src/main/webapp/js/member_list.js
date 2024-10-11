function agree_ajax() {
	var html = new XMLHttpRequest();
	html.onreadystatechange = function() {
		if (html.readyState == 4 && html.status == 200) {
			var result = JSON.parse(this.response);
			mlist_pagefrm.agree_use.value = result["useAgree"];
			mlist_pagefrm.agree_info.value = result["infoAgree"];
		}
	}
	html.open("GET", "./get_agree.do", true);
	html.send();
}

function useck_update() {
	if (mlist_pagefrm.agree_use.value == "") {
		alert("이용약관을 작성해야 수정할 수 있습니다.");
		mlist_pagefrm.agree_use.focus();
	}
	else {
		if (confirm("정말로 이용약관을 수정하시겠습니까?")) {
			mlist_pagefrm.agree_ck.value = "use";
			mlist_pagefrm.method = "POST";
			mlist_pagefrm.action = "./agree_update.do";
			mlist_pagefrm.submit();
		}
	}
}

function infock_update() {
	if (mlist_pagefrm.agree_info.value == "") {
		alert("개인정보 약관을 작성해야 수정할 수 있습니다.");
		mlist_pagefrm.agree_info.focus();
	}
	else {
		if (confirm("정말로 개인정보 약관을 수정하시겠습니까?")) {
			mlist_pagefrm.agree_ck.value = "info";
			mlist_pagefrm.method = "POST";
			mlist_pagefrm.action = "./agree_update.do";
			mlist_pagefrm.submit();
		}
	}
}

var loginck = document.getElementById("loginck");
var valueck = document.getElementById("valueck");

function userlogin_no(no) {
	if (confirm("정말로 해당 회원의 계정을 휴면 상태로 변경하시겠습니까?")) {
		login_updateck(no, "휴면");
	}
}

function userlogin_ok(no) {
	if (confirm("정말로 해당 계정의 휴면 상태를 해제하시겠습니까?")) {
		login_updateck(no, "정상");
	}
}

function login_updateck(no, ck) {
	loginck.value = no;
	valueck.value = ck;
	mlist_pagefrm.method = "POST";
	mlist_pagefrm.action = "./userlogin_update.do";
	mlist_pagefrm.submit();
}

agree_ajax();