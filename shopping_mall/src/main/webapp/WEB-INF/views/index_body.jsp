<%@page import="java.util.Date"%>
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
       .btn_button {
            width: 150px;         /* 가로 사이즈 50px */
            height: 100px;        /* 높이 60px */
            background-color: gray; /* 배경색 녹색 */
            color: white;        /* 글자색 흰색 */
            border: none;        /* 테두리 없음 */
            border-radius: 5px;  /* 모서리 둥글게 */
            font-size: 16px;     /* 글자 크기 */
            cursor: pointer;     /* 마우스 포인터가 손 모양으로 변경 */
            display: flex;       /* Flexbox를 사용하여 중앙 정렬 */
            align-items: center; /* 수직 중앙 정렬 */
            justify-content: center; /* 수평 중앙 정렬 */
            transition: background-color 0.3s ease; /* 배경색 변화 시 애니메이션 */
        }
       
    </style>

</head>
<script src="./js/jquery.js"></script>
<body>
<form id="alogin_frm">
    <section class="admin_bgcolor">
        <div class="admin_login">
            <span>
                <div class="left_div">
                <ul>
                <li><input type="text" id="aid" name="aid" class="input_text1" placeholder="관리자 아이디를 입력하세요"></li>
                <li><input type="password" id="apass" name="apass" class="input_text1" placeholder="관리자 패스워드를 입력하세요"></li>
                </ul>
                </div>
                <div class="right_div">
                    <input type="button" class="btn_button" id="btn6"  title="MASTER LOGIN" value="MASTER LOGIN" onclick="login_admin()">
                </div>
                <em class="alert_msg">※ 본 사이트는 관리자 외에는 접근을 금합니다. 페이지 접근에 대한 접속 정보는 모두 기록 됩니다.</em>
            </span>
            <span>
                <ol class="admin_info">
                    <li title="신규 관리자 등록요청"><a href="/admin/add_master.do">신규 관리자 등록요청</a></li>
                    <li title="아이디/패스워드 찾기">아이디/패스워드 찾기</li>
                </ol>                
            </span>
        </div>
    </section>
</form>
</body>
<%
Date today = new Date();
%>
<script src="/js/index_body.js?v=<%=today%>"></script>
</html>