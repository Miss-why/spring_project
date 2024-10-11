function file_down(oriname, finame) {
	location.href = "./notice_filedown.do?oname="
		+ oriname + "&fname=" + finame;
}

function go_listPage() {
	location.href = "./notice_list.do";
}

function update_notice() {
	location.href = "./notice_modify.do";
}

function delete_notice() {
	if (confirm("공지사항 데이터 삭제시 복구 되지 않습니다.")) {
		notice_del_frm.method = "POST";
		notice_del_frm.action = "./notice_delete.do";
		notice_del_frm.submit();
	}
}