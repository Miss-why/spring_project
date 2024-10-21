var duplicode_ck = "";

function codeck_ajax() {
	var product_code = document.getElementById("product_code");
	if (product_code.value == "") {
		alert ("상품코드는 필수입니다.");
		product_code.focus();
	}
	var html = new XMLHttpRequest();
	duplicode_ck = "";
	
	html.onreadystatechange = function() {
		if (html.readyState == 4 && html.status == 200) {
			duplicode_ck = this.response;
			if (duplicode_ck == "ok") {
				if (confirm("사용 가능한 상품 코드입니다.\n확정하시겠습니까?")) {
					product_code.readOnly = true;
				}
			}
			else {
				alert("사용 불가능한 상품코드입니다.");
			}
		}
	}
	html.open("POST", "./producode_check.do", true);
	html.send("product_code=" + product_code.value);
}

function make_product_code() {
	var procode = "";
	var w = 0;
	while (w < 7) {
		procode += Math.floor(Math.random() * 9);
		w++;
	}
	document.getElementById("product_code").value = procode;
}

function js_listpage(no) {
	if (no == 1) {
		if (confirm("페이지를 이동하면 작성 내용이 저장되지 않습니다.\n그래도 이동하시겠습니까?")) {
			location.href = "./cate_list.do";
		}
	}
	else if (no == 2){
		if (confirm("페이지를 이동하면 작성 내용이 저장되지 않습니다.\n그래도 이동하시겠습니까?")) {
			location.href = "./product_list.do";
		}
	}
}

function js_pinsert() {
	const regex_number = /^\d+$/; // 숫자 판별 정규식 코드
	if (product_frm.pcateidx.value == "") {
		alert("대메뉴 카테고리를 선택하세요.");
		product_frm.pcateidx.focus();
	}
	else if (product_frm.pcode.value == "" || !product_frm.pcode.readOnly) {
		alert("상품 코드의 중복 확인을 해주세요.");
		product_frm.pcode.focus();
	}
	else if (product_frm.pname.value == 0) {
		alert("상품명을 입력하세요.");
		product_frm.pname.focus();
	}
	else if (product_frm.add_pexplain.value == 0) {
		alert("상품 부가 설명을 입력하세요.");
		product_frm.add_pexplain.focus();
	}
	else if (!regex_number.test(product_frm.original_price.value) || product_frm.original_price.value <= 100) {
		alert("판매 가격을 입력하세요.");
		product_frm.original_price.focus();
	}
	else if (!regex_number.test(product_frm.disrate.value)) {
		alert("할인율은 숫자만 입력하세요.");
		product_frm.disrate.focus();
	}
	else if (!regex_number.test(product_frm.product_stock.value)) {
		alert("할인율은 숫자만 입력하세요.");
		product_frm.product_stock.focus();
	}
	else if (product_frm.imgfile[0].files.length == 0) {
		alert("상품 대표 이미지는 필수입니다.");
	}
	else if (product_frm.detail_pexplain.value == 0) {
		alert("상품 상세 설명을 입력하세요.");
		product_frm.detail_pexplain.focus();
	}
	else {
		file_check();
	}
}

function file_check() {
	var imgfile = document.getElementsByName("imgfile");
	var fproperty = ["png", "jpeg", "jpg", "gif", "webp", "svg", "bmp"];
	var result = "";
	var w = 0;
	while (w < 3) {
		var ck = "";
		if (imgfile[w].files.length != 0) {
			ck = "no";
			if (imgfile[w].files[0].size > 2097152) {
				result += "no";
				break;
			}
			else {
				var filetype = imgfile[w].files[0].type;
				var a = 0;
				do {
					if (filetype.indexOf(fproperty[a]) >= 0) {
						ck = "ok";
						break;
					}
					a++;
				} while (a < fproperty.length);
			}
			result += ck;
		}
		w++;
	}
	if (result.indexOf("no") >= 0) {
		alert("상품 이미지는 2MB 이하의 이미지 파일만 가능합니다.");
	}
	else {
		if (confirm("입력한 내용으로 상품을 등록하시겠습니까?")) {
			product_frm.method = "POST";
			product_frm.action = "./product_insert.do";
			product_frm
			product_frm.submit();
		}
	}
}

function price_update() {
	var oprice = document.getElementById("oprice").value;
	var disrate = document.getElementById("disrate").value;
	var disprice = document.getElementById("disprice");
	if (disrate == 0) {
		disprice.value = 0;
	}
	else {
		disprice.value = Math.floor(oprice * (100 - disrate) / 100);
	}
}

make_product_code();