<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 내용 확인 페이지</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=10">
    <link rel="stylesheet" type="text/css" href="./css/main.css?v=10">
    <link rel="stylesheet" type="text/css" href="./css/notice.css?v=10">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
</head>
<body>
<%@ include file="./admin_top.jsp" %>
<main class="maincss">
<section>
    <p>공지사항 확인 페이지</p>
<div class="write_view">
<ul>
    <li>공지사항 제목</li>
    <li>
    	${ notidata.getNtitle() }
    </li>
</ul>
<ul>
    <li>글쓴이</li>
    <li>
     	${ notidata.getNamname() }
    </li>
</ul>
<ul>
    <li>첨부파일</li>
    <li>
    	<cr:if test="${ notidata.getNfile() != '' }">
	    	<cr:set var="notifile" value="${ fn:split(notidata.getNfile(), '|') }"/>
			${ notifile[0] }
			<button type="button" onclick="file_down('${ notifile[0] }', '${ notifile[1] }')" class="btn_button">다운로드</button>
    	</cr:if>
    </li>
</ul>
<ul class="ul_height">
    <li>공지내용</li>
    <li>
        <div class="notice_input3" style="overflow-y: auto;">${ notidata.getNtext() }</div>
    </li>
</ul>
</div>
<div class="board_btn">
    <button class="border_del" onclick="go_listPage()">공지목록</button>
    <button class="border_add" onclick="update_notice()">공지수정</button>
    <button class="border_modify" onclick="delete_notice()" style="margin-left: 8px;">공지삭제</button>
</div>
</section>
<form id="notice_del_frm">
	<input type="hidden" name="del_ck" value="${ notidata.getNidx() }">
</form>
</main>
<footer class="main_copyright">
	<%@ include file="./admin_footer.jsp" %>
</footer>
</body>
<script src="./js/notice_view.js?v=1"></script>
</html>