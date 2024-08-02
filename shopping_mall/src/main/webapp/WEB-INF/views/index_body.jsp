<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <style>
        /* 기본 링크 스타일 */
        a {
            text-decoration: none; /* 기본 밑줄 제거 */
            color: #007bff; /* 기본 텍스트 색상 */
            font-weight: bold; /* 글자 두껍게 */
            padding: 5px; /* 패딩 추가 */
            border-radius: 3px; /* 모서리 둥글게 */
        }

        /* 링크에 마우스를 올렸을 때의 스타일 */
        a:hover {
            color: #0056b3; /* 마우스 오버 시 텍스트 색상 변경 */
            cursor: pointer; /* 커서를 손 모양으로 변경 */
            background-color: #e2e6ea; /* 배경 색상 변경 */
        }

        /* 링크에 클릭했을 때의 스타일 */
        a:active {
            color: #003d7a; /* 클릭 시 텍스트 색상 변경 */
            background-color: #d0d0d0; /* 클릭 시 배경 색상 변경 */
        }
    </style>

</head>
<body>
<form id="frm">
    <section class="admin_bgcolor">
        <div class="admin_login">
            <span>
                <div class="left_div">
                <ul>
                <li><input type="text" name=aid class="input_text1" placeholder="관리자 아이디를 입력하세요"></li>
                <li><input type="password" name=apass class="input_text1" placeholder="관리자 패스워드를 입력하세요"></li>
                </ul>
                </div>
                <div class="right_div">
                    <button type="submit" class="btn_submit" title="MASTER LOGIN" onclick="login_admin()">MASTER LOGIN</button>
                </div>
                <em class="alert_msg">※ 본 사이트는 관리자 외에는 접근을 금합니다. 페이지 접근에 대한 접속 정보는 모두 기록 됩니다.</em>
            </span>
            <span>
                <ol class="admin_info">
                    <li title="신규 관리자 등록요청"><a href="/admin/add_master.do" >신규 관리자 등록요청</a></li>
                    <li title="아이디/패스워드 찾기">아이디/패스워드 찾기</li>
                </ol>                
            </span>
        </div>
    </section>
</form>
</body>
<script>
function login_admin() {
	frm.method="post";
	frm.action="./admin_main.do";
	frm.submit();
}
</script>
</html>