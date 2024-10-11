var arr = [["10%","20%","30%"],["30%","40%","50%"]];

// 숙제 데이터
var basket = [
	{"seq" : "1", "product" : "냉장고", "price" : "195000"},
	{"seq" : "2", "product" : "세탁기", "price" : "287000"},
	{"seq" : "10", "product" : "에어프라이어", "price" : "97000"}
];

$(function() {
	// JSON.stringify : Object 형태로 전달
	// join : 문자열로 변환
	
	// Front 배열 보내기 응용편
	$("#btn3").click(function() {
		
		$.ajax({
			url : "./ajaxok3.do",
			type : "GET",
			cache : false,
			dataType : "TEXT",
			contentType : "application/json",
			data : { basket : JSON.stringify(basket) },
			success : function($result) {
				console.log($result);
			},
			error : function() {
				console.log("error!!");
			}
		});
	});
	
	$("#btn2").click(function() {
		var $data = new Array();
		$data[0] = "홍길동";
		$data[1] = "강감찬";
		$data[2] = "이순신";
		
		$.ajax({
			url : "./ajaxok2.do",
			type : "POST",
			cache : false,
			dataType : "JSON",
			contentType : "application/json",
			data : JSON.stringify({
				"all_data" : $data
			}),
			success : function($result) {
				console.log($result);
			},
			error : function() {
				console.log("error!!");
			}
		});
	});
	
	$("#btn").click(function() {
		var $data = new Array();
		$data[0] = "20";
		$data[1] = "30";
		$data[2] = "40";
		//console.log($data.join(","));
		
		$.ajax({
			url : "./ajaxok.do",
			cache : false,
			type : "GET",
			dataType : "JSON",
			contentType : "application/json",
			data : {
				"all_data" : $data.join(",")
			},
			success : function($result) {
				console.log($result);
			},
			error : function() {
				console.log("error!!");
			}
		});
	});
});