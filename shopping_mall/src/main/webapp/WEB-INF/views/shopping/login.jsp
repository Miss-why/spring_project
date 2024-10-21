<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>${ hpInfo.getHometitle() }</title>
    <meta charset="utf-8" />
    <link href="../css/shopping/index.css" rel="stylesheet" />
    <link href="../css/shopping/subpage.css" rel="stylesheet" />
  </head>

  <body>
<%@ include file="./top.jsp" %>
 <main>
    <div class="products">
      <h3>MEMBER_LOGIN</h3>
      <div class="sub_view">
        
        <div class="loginview">
          <span class="member_l"></span>
          <span class="login">
          <form id="login_frm" onsubmit="return user_login()">   
          <table border="0" cellpadding="0" cellspacing="0" class="table_login">
          <tr>
          <td>
          <input type="text" name="user_id" placeholder="아이디를 입력해주세요" class="id">
          </td>
          <td rowspan="2">
          <input type="submit" value="LOGIN" class="btn1">
          </td>
          </tr>
          <tr>
          <td>
          <input type="password" name="user_pw" placeholder="패스워드를 입력해주세요" class="id">
          </td>
          </tr>
          </table>
              
          <div class="check1">
          <input type="checkbox" name="save_uid" value="Y" id="l"> <label for="l" class="label_login">아이디 저장</label>
          </div>
          </form>
          <ul class="btns_login">
          <li><input type="button" value="회원가입" onclick="page_location('1')" class="a"></li>
          <li><input type="button" value="아이디 찾기" onclick="page_location('2')" class="a"></li>
          <li><input type="button" value="비밀번호 찾기" onclick="page_location('3')" class="a1"></li>
          </ul>
          <form id="nonlogin_user" onsubmit="return non_login()">    
          <span class="font1">비회원 주문조회</span>
          <span class="search_login">
              <table border="0" cellpadding="0" cellspacing="0" class="table_login">
              <tr>
              <td>
                  <input type="text" placeholder="주문자명을 입력해주세요" class="id" tabindex="1">
              </td>
              <td rowspan="2">
              <input type="submit" value="주문조회" class="btn1"></td>
              </tr>
              <tr>
                  <td>
                 <input type="text" placeholder="주문번호를 8자리 입력해 주세요" class="id" maxlength="8" tabindex="2">
                  </td>
                  </tr>
              </table>
              <label class="check1">
              주문자명과 주문번호를 잊으신 경우, 고객센터로 문의하여 주시기 바랍니다.
          </label>
          </span>
          </form>
          </span>
      </div>

      </div>
    </div>
</main>
<%@ include file="./footer.jsp" %>
  </body>
<script src="../js/shopping/login.js?v=2"></script>
</html>