function agree_ajax() {
	var html = new XMLHttpRequest();
	html.onreadystatechange = function() {
		if (html.readyState == 4 && html.status == 200) {
			var result = JSON.parse(this.response);
			document.getElementById("use_agree").innerText = result["useAgree"];
			document.getElementById("info_agree").innerText = result["infoAgree"];
		}
	}
	html.open("GET", "./get_agree.do", true);
	html.send();
}

var checkCount = 2;
var count = 0;
document.querySelector("#allAgree").addEventListener("click", function() {
	var w = 1;
	while (w <= checkCount) {
		document.querySelector("#termsAgree" + w).checked = this.checked;
		if (this.checked) {
			count = checkCount;
		}
		w++;
	}
});

var n = 1;
while (n <= checkCount) {
	document.querySelector("#termsAgree" + n).addEventListener("click", function() {
		count = 0;
		var w = 1;
		while (w <= checkCount) {
			if (document.querySelector("#termsAgree" + w).checked) {
				count++;
			}
			w++;
		}
		document.querySelector("#allAgree").checked = (count == checkCount) ? true : false;
	});
	n++;
}

document.querySelector("#btnNextStep").addEventListener("click", function() {
	if (!agree_frm.agreementInfoFl.checked || !agree_frm.privateApprovalFl.checked) {
		alert("이용약관과 개인정보 수집 및 이용에 대한 안내 모두 동의해주세요.");
	}
	else {
		agree_frm.method = "POST";
		agree_frm.action = "./join.do";
		agree_frm.submit();
	}
});

agree_ajax();