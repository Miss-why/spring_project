<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항 리스트 페이지</title>
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
<form id="checkbox_frm">
<main class="maincss">
<section>
    <p>공지사항 관리페이지</p>
    <div class="subpage_view">
    <ul>
        <li><input type="checkbox" id="all_ck" onclick="checkbox_allck()"></li>
        <li>NO</li>
        <li>제목</li>
        <li>글쓴이</li>
        <li>날짜</li>
        <li>조회</li>
    </ul>
    <cr:if test="${ noticelist.size() == 0 }">
	    <ol class="none_text">
	        <li>등록된 공지 내용이 없습니다.</li>
	    </ol>
    </cr:if>
    <cr:if test="${ noticelist.size() != 0 }">
    	<cr:set var="pageidx" value="${ dataCount - startNumber }"/>
    	<cr:forEach var="notidata" items="${ noticelist }" varStatus="stat">
		    <ol>
		        <li><input type="checkbox" name="del_ck" value="${ notidata.getNidx() }" onclick="checkbox_eachck()"></li>
		        <li>${ pageidx - stat.index }</li>
		        <li onclick="go_vpage('${ notidata.getNidx() }')">${ notidata.getNtitle() }</li>
		        <li>${ notidata.getNamname() }</li>
		        <li>${ fn:substring(notidata.getNindate(), 0, 10) }</li>
		        <li>${ notidata.getNviews() }</li>
		    </ol>
		</cr:forEach>
	</cr:if>
    </div>
    <div class="board_btn">
        <button type="button" class="border_del" onclick="check_delete('1')">공지삭제</button>
        <button type="button" class="border_add" onclick="go_wpage()">공지등록</button>
    </div>
    <div class="border_page">
        <ul class="pageing">
            <cr:set var="pg_limit" value="${ pg_limit }"/>
            <cr:set var="pg" value="${ dataCount / maxcount + (1 - (dataCount / maxcount) % 1) % 1 }"/>
            <cr:if test="${ page != 1 }">
            	<li onclick="js_selectpage('1')"><img src="./ico/double_left.svg"></li>
            </cr:if>
            <cr:if test="${ pg_start > maxcount }">
            	<li onclick="js_selectpage('${ pg_start - 1 }')"><img src="./ico/left.svg"></li>
            </cr:if>
            <cr:forEach var="cnt" begin="${ pg_start }" end="${ pg_end }" step="1">
            	<cr:if test="${ cnt <= pg }">
		            <li onclick="js_selectpage('${ cnt }')">${ cnt }</li>
            	</cr:if>
            </cr:forEach>
            <cr:if test="${ pg_end < pg }">
            	<li onclick="js_selectpage('${ pg_end + 1 }')"><img src="./ico/right.svg"></li>
            </cr:if>
            <cr:if test="${ page != pg }">
            	<li onclick="js_selectpage('${ pg }')"><img src="./ico/double_right.svg"></li>
            </cr:if>
        </ul>
    </div>
</section>
</main>
</form>
<footer class="main_copyright">
	<%@ include file="./admin_footer.jsp" %>
</footer>
</body>
<script src="./js/list_checkbox.js?v=4"></script>
<script>
	function go_vpage(no) {
		location.href='./notice_view.do?nidx=' + no;
	}
	
	function go_wpage() {
		location.href = "./notice_write.do";
	}
	
	function js_selectpage(no) {
		location.href = "./notice_list.do?page=" + Number(no);
	}
</script>
</html>