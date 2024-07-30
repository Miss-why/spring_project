<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 정보 찾기</title>
</head>
<body>
<form id="frm">
<p>아이디 찾기</p>
고객명 : <input type="text" name="uname"><br>
가입이메일 : <input type="text" name="uemail"><br>
<input type="button" value="아이디 찾기" onclick="btn1()"> 
<p>패스워드 찾기</p>
아이디 : <input type="text" name="uid"><br>
고객명 : <input type="text" name="uname"><br>
<input type="button" value="패스워드 변경" onclick="btn2()"> 
</form>

</body>
<script>
//하나의 form에 여러개의 action을 걸 수도 있음 (단 form 한개로 여러 정보 날릴때면 name값 같이 쓰면 배열로 날라가서 중복날 수 있음!! (주의))
//만약 name값 같이 쓰면 배열로 날라가므로 controller에서 받을때 배열로 받으면 됨
function btn1() {
	frm.method="post";
	frm.action="./idsearch.do";
	frm.submit();
}
function btn2() {
	frm.method="post";
	frm.action="./passmodify.do";
	frm.submit();
}

</script>

</html>