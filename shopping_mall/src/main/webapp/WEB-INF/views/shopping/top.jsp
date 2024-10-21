<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
	<div class="navbar">
		<a href="#" id="logo">
			<img src="../images/logo.jpg" width="149">
		</a>
		<ul id="menu">
			<cr:if test="${ userName == null || userName == ''}">
			<li><a href="./login">LOGIN</a></li>
			<li><a href="#">MEMBER SHIP</a></li>
			</cr:if>
			<cr:if test="${ userName != null && userName != ''}">
			<li>${ userName }님 환영합니다. <a href="./user_logout.do">[로그아웃]</a></li>
			</cr:if>
			<li><a href="#">CART</a></li>
			<li><a href="#">CUSTOMER CENTER</a></li>
		</ul>
	</div>
</header>