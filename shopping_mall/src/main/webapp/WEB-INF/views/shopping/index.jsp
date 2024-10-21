<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>${ hpInfo.getHometitle() }</title>
    <meta charset="utf-8" />
    <link href="../css/shopping/index.css?v=1" rel="stylesheet" />
    <link href="../css/shopping/menu.css?v=1" rel="stylesheet"/>
  </head>

  <body>
<%@ include file="./top.jsp" %>
<nav>
	<div class="menu-list">
		<ul>
			<cr:forEach var="category" items="${ catelist }">
				<li>${ category.getClgmenu_name() }</li>
			</cr:forEach>
		</ul>
	</div>
</nav>
 <main>
    <div class="hero-header"></div>  
    <div class="products">
      <h3>NEW PRODUCTS</h3>
      <div class="product-list">
      	<cr:forEach var="product" items="${ productlist }">
	        <a href="#" class="product">
	          <cr:set var="pimg" value="${ fn:split(product.getPimages(), '|') }"/>
	          <img src="../product_img/${ pimg[1] }" width="225" height="225">
	          <div class="product-name">
	            ${ product.getPname() }
	          </div>
	          <cr:choose>
	         	<cr:when test="${ product.getDiscount_price() == 0 }">
				  <div class="product-price">${ product.getOriginal_price() }</div>
	          	</cr:when>
	          	<cr:otherwise>
	          	  <div class="product-price">${ product.getDiscount_price() }</div>
	          	</cr:otherwise>
	          </cr:choose>
	        </a>
      	</cr:forEach>
        <div class="clearfix"></div>
      </div>
    </div>
</main>
<%@ include file="./footer.jsp" %>
  </body>
</html>