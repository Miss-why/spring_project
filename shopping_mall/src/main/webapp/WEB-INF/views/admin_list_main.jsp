<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<main class="maincss">
<section>
    <p>신규등록 관리자</p>
    <ol class="new_admin_title2">
        <li>NO</li>
        <li>관리자명</li>
        <li>아이디</li>
        <li>전화번호</li>
        <li>이메일</li>
        <li>담당부서</li>
        <li>담당직책</li>
        <li>가입일자</li>
        <li>승인여부</li>
    </ol>
    <ol class="new_admin_none">
        <li>신규 등록된 관리자가 없습니다.</li>
    </ol>
    <ol class="new_admin_lists2">
        <cr:set var="ano" value="${total-startpg}"/>
		<cr:forEach var="data" items="${all}" varStatus="aidx">
        <li>${ano-aidx.index}</li>
        <li>${data.get(1)}</li>
        <li>${data.get(2)}</li>
        <li>${data.get(3)}</li>
        <li>${data.get(4)}</li>
        <li>${data.get(5)}</li>
        <li>${data.get(6)}</li>
        <li>${data.get(7)}</li>
        <li>${data.get(8)}</li>
		</cr:forEach>
		<br><br>
        <li>
            <input type="button" value="승인" class="new_addbtn1" title="승인">
            <input type="button" value="미승인" class="new_addbtn2" title="미승인">
        </li>
    </ol>
    <!-- 페이지 번호 출력 -->
	<table>
	<tr>
	<cr:set var="pg" value="${total/15+(1-((total/15)%1))%1}"/> <!-- 나누는 몫(3) : 한 페이지 당 몇개가 나오게 할 것인가 -->
	<cr:forEach var="no" begin="1" end="${pg}" step="1">
	<td><a href="./coupon_list1.do?page=${no}">${no}</a></td>
	</cr:forEach>
</tr>
</table>
</section>
<section></section>
<section></section>
</main>
</body>
</html>