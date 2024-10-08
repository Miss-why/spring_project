var arr = [["10%","20%","30%"],["30","40","50"]];

//숙제 데이터
var basket = [
	{"seq":"1", "product":"냉장고", "price":"195000"},
	{"seq":"2", "product":"세탁기", "price":"287000"},
	{"seq":"10", "product":"에어프라이어", "price":"97000"}
];


$(function(){
	//Front 배열값 응용편
	$("#btn3").click(function(){
	
	$.ajax({
		url:"./ajaxok3.do",
		type:"post",
		cache:false,
		dataType:"text",
		contentType:"application/json",
		data:JSON.stringify(arr),
		success:function($result){
			console.log($result);
		},
		error:function(){
			console.log("error!!");
		}
	});	
});
	
	
	$("#btn2").click(function(){
		var $data = new Array();
		$data[0] = "홍길동";
		$data[1] = "강감찬";
		$data[2] = "이순신";
		
		//JSON.stringify : Object 형태로 전달
		$.ajax({
			url:"./ajaxok2.do",
			type:"post",
			cache:false,
			dataType:"json",
			contentType:"application/json",
			data:JSON.stringify({
				"all_data": $data
			}),
			successfunction($result){
				console.log($result);
			},
			error:function(){
				console.log("error!!");
			}
		});
		
	});
	//get전송방식
	$("#btn").click(function(){
		var $data = new Array();
		$data[0]="20";
		$data[1]="30";
		$data[2]="40";
		//join -> JSON.stringfy 형태로 변화
		//console.log($data.join(",")); //join:배열이 문자열로 바뀐다.(리액트에 많이 쓰인다.)
		$.ajax({
			url:"./ajaxok.do",
			cache:false,
			dataType:"json",//데이터타입은 백엔드가 어떻게 주느냐에 따라서 달라진다.(백엔드가 데이터타입을 텍스트로 드릴게요라고 하면 text라고 적어야됨)
			contentType:"application/json", //application/json;charset=utf-8이렇게 적을 수도 있는데 굳이 안 적어도 된다.이미 다 언어셋 설정을 했기 때문
			type:"post",
			data:{
				"all_data":$data.join(",") //json.Stringfy 안 쓰고 이렇게 넘긴다.//alldata가 key이름이다.백엔드 value랑 맞춰야됨
			},
			success:function($result){
				console.log($result)
			},
			error:function(){
				console.log("error!!");
			}
		});
	});
});