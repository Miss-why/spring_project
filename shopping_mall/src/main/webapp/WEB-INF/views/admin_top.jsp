<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="headercss">
    <div class="header_div">
        <p><img src="./img/logo.png" class="logo_sm"> ADMINISTRATOR</p>
        <p>${ adminSessionData.get(3) } 관리자 <a href="#">[개인정보 수정]</a> <a href="./admin_logout.do">[로그아웃]</a></p>
    </div>
</header>
<nav class="navcss">
    <div class="nav_div">
        <ol>
        	<cr:if test="${ adminSessionData.get(1) == '1' }">
	            <li title="쇼핑몰 상품관리" onclick="js_topnav_click('1')">쇼핑몰 관리자 리스트</li>
        	</cr:if>
            <li title="쇼핑몰 회원관리" onclick="js_topnav_click('2')">쇼핑몰 회원관리</li>
            <li title="쇼핑몰 상품관리" onclick="js_topnav_click('3')">쇼핑몰 상품관리</li>
            <li title="쇼핑몰 기본설정" onclick="js_topnav_click('4')">쇼핑몰 기본설정</li>
            <li title="쇼핑몰 공지관리" onclick="js_topnav_click('5')">쇼핑몰 공지관리</li>
        </ol>
    </div>
</nav>
<script src="./js/admin_top.js?v=4"></script>