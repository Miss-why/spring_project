function go_list() {
	if (confirm("현재 입력값은 저장 되지 않습니다.\n리스트 페이지로 돌아가시겠습니까?")) {
		location.href = './cate_list.do';
	}
}

function js_cate_create() {
	if (cate_frm.cls_code.value == "" || cate_frm.clgmenu_code.value == "") {
		alert("대메뉴 코드를 입력하세요.");
		cate_frm.clgmenu_code.focus();
	}
	else if (cate_frm.clgmenu_name.value == "") {
		alert("대메뉴명을 입력하세요.");
		cate_frm.clgmenu_name.focus();
	}
	else {
		if (confirm("정말로 카테고리를 등록하시겠습니까?")) {
			cate_frm.method = "POST";
			cate_frm.action = "./cate_writeok.do";
			cate_frm.submit();
		}
	}
}