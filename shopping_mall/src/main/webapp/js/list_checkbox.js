var delck = document.getElementsByName("del_ck");
var allck = document.getElementById("all_ck");
var count = 0;
function checkbox_allck() {
	var w = 0;
	while (w < delck.length) {
		delck[w].checked = allck.checked;
		if (delck[w].checked) {
			count++;
		}
		w++;
	}
}

function checkbox_eachck() {
	count = 0;
	var w = 0;
	while (w < delck.length) {
		if (delck[w].checked) {
			count++;
		}
		w++;
	}
	if (count == delck.length) {
		allck.checked = true;
	}
	else {
		allck.checked = false;
	}
}

function check_delete(no) {
	if (count < 1) {
		alert("선택한 항목이 존재하지 않습니다.");
	}
	else {
		if (confirm("삭제 시, 해당 데이터는 복구할 수 없습니다.\n정말로 선택한 항목을 삭제하시겠습니까?")) {
			if (no == 1) {
				checkbox_frm.action = "./notice_delete.do";
			}
			else {
				checkbox_frm.action = "./checkbox_delete.do";
			}
			checkbox_frm.method = "POST";
			checkbox_frm.submit();
		}
	}
}