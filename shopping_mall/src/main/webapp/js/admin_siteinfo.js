function hp_info_save() {
	const regex_number = /^\d+$/; // 숫자 판별 정규식 코드
	if (homepage_info_frm.hometitle.value == "") {
		alert("홈페이지 제목을 입력하세요.");
		homepage_info_frm.hometitle.focus();
	}
	else if (homepage_info_frm.adminemail.value == "") {
		alert("관리자 메일 주소를 입력하세요.");
		homepage_info_frm.adminemail.focus();
	}
	else if (!regex_number.test(homepage_info_frm.signpoint.value)) {
		alert("회원가입시 적립금을 숫자로 입력하세요.");
		homepage_info_frm.signpoint.focus();
	}
	else if (!regex_number.test(homepage_info_frm.signlevel.value)) {
		alert("회원가입시 권한레벨을 입력하세요.");
		homepage_info_frm.signlevel.focus();
	}
	else if (homepage_info_frm.compamy_name.value == "") {
		alert("회사명을 입력하세요.");
		homepage_info_frm.compamy_name.focus();
	}
	else if (homepage_info_frm.business_regist_number.value == "") {
		alert("사업자등록번호를 입력하세요.");
		homepage_info_frm.business_regist_number.focus();
	}
	else if (homepage_info_frm.president_name.value == "") {
		alert("대표자명을 입력하세요.");
		homepage_info_frm.president_name.focus();
	}
	else if (homepage_info_frm.president_phone.value == "") {
		alert("대표전화번호를 입력하세요.");
		homepage_info_frm.president_phone.focus();
	}
	else if (!regex_number.test(homepage_info_frm.business_zipcode.value)) {
		alert("사업장 우편번호를 입력하세요.");
		homepage_info_frm.business_zipcode.focus();
	}
	else if (homepage_info_frm.business_address.value == "") {
		alert("사업장 주소를 입력하세요.");
		homepage_info_frm.business_address.focus();
	}
	else if (homepage_info_frm.infomanager_name.value == "") {
		alert("정보관리책임자명을 입력하세요.");
		homepage_info_frm.infomanager_name.focus();
	}
	else if (homepage_info_frm.infomanager_email.value == "") {
		alert("정보책임자 E-mail을 입력하세요.");
		homepage_info_frm.infomanager_email.focus();
	}
	else if (homepage_info_frm.non_account_bank.value != "" && homepage_info_frm.bank_account_number.value == "") {
		alert("무통장 은행 사용을 위한 은행 계좌 번호를 입력하세요.");
		homepage_info_frm.bank_account_number.focus();
	}
	else if (!regex_number.test(homepage_info_frm.min_pay_points.value) || homepage_info_frm.min_pay_points.value.length < 4) {
		alert("결제 최소 포인트를 1000원 이상 입력하세요.");
		homepage_info_frm.min_pay_points.focus();
	}
	else if (!regex_number.test(homepage_info_frm.max_pay_points.value) || homepage_info_frm.min_pay_points.value >= homepage_info_frm.max_pay_points.value) {
		alert("결제 최대 포인트는 결제 최소 포인트보다 높아야 합니다.");
		homepage_info_frm.max_pay_points.focus();
	}
	else if (homepage_info_frm.delivery_compamy_name.value == "") {
		alert("배송업체명을 입력하세요.");
		homepage_info_frm.delivery_compamy_name.focus();
	}
	else if (!regex_number.test(homepage_info_frm.delivery_fee_price.value)) {
		alert("배송비 가격을 숫자로 입력하세요.");
		homepage_info_frm.delivery_fee_price.focus();
	}
	else {
		if (homepage_info_frm.non_account_bank.value == "") {
			homepage_info_frm.bank_account_number.value = "";
		}
		if (confirm("작성하신 설정을 저장하시겠습니까?")) {
			homepage_info_frm.method = "POST";
			homepage_info_frm.action = "./hpinfo_update.do";
			homepage_info_frm.submit();
		}
	}
}

function save_cancel() {
	window.location.reload();
}