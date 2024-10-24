<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <title>${ hpInfo.getHometitle() }</title>
    <meta charset="utf-8" />
    <link href="../css/shopping/index.css" rel="stylesheet" />
    <link href="../css/shopping/subpage.css" rel="stylesheet" />
    <link href="../css/shopping/agree.css?v=1" rel="stylesheet" />
    <link href="../css/shopping/join.css?v=1" rel="stylesheet" />
  </head>

  <body>
<%@ include file="./top.jsp" %>
 <main>
    <div class="products">
    <h3>MEMBER_JOIN</h3>
    <div class="sub_view">
   
    <div class="joinview">     
    <form id="join_frm">
    <input type="hidden" name="ckagree_use" value="${ agreementInfoFl }">
    <input type="hidden" name="ckagree_info" value="${ privateApprovalFl }">
    <h3>회원가입</h3>
	<span class="join_info">
    <ol>
    <li>기본정보 </li>
    <li>※ 표시는 반드시 입력하셔야 하는 항목 입니다.</li>
    </ol>
    </span>
    <ul class="join_ul">
    <li>※ 아이디</li>
    <li>
    <input type="text" name="uid" id="user_id" class="join_in1"> <input type="button" id="duplidBtn" value="중복확인" class="join_btn1">
    </li>
    <li>※ 비밀번호</li>
    <li>
    <input type="password" name="upass" class="join_in1 join_in2"> ※ 최소 6자 이상 입력하셔야 합니다.
    </li>
    <li>※ 비밀번호 확인</li>
    <li>
    <input type="password" id="pass_ck" class="join_in1 join_in2"> ※ 동일한 패스워드를 입력하세요.
    </li>
    <li>※ 이름</li>
    <li>
    <input type="text" name="uname" class="join_in1">
    </li>
    <li>※ 이메일</li>
    <li>
    <input type="text" name="uemail" id="user_email" class="join_in1"> <input type="button" id="emailCheckBtn" value="이메일 인증" class="join_btn1"> ※ 입력하신 이메일을 확인해 주세요.
    </li>
    <li>※ 인증번호</li>
    <li>
    <input type="text" id="user_code" class="join_in1 join_in3" maxlength="8"> ※ 8자리 인증번호를 입력하세요.
    </li>
    <li>※ 전화번호</li>
    <li>
    <input type="text" name="uphone" class="join_in1 join_in2" maxlength="11"> ※ 숫자만 입력하세요
    </li>
    <li>※ 이벤트 메일 수신</li>
    <li>
    <label><input type="checkbox" name="ckuse_email" value="Y" class="join_ck1"> 정보/이벤트 메일 수신에 동의합니다.</label>
    </li>
    <li>※ 이벤트 SMS 수신</li>
    <li>
    <label><input type="checkbox" name="ckuse_phone" value="Y" class="join_ck1"> 정보/이벤트 SMS 수신에 동의합니다.</label>
    </li>
    </ul>
    <div class="btn_center_box">
    <button type="button" id="btnNextStep" class="btn_join">회원가입</button>
    </div>
	</form>
    </div>
    </div>
    </div>
</main>
<%@ include file="./footer.jsp" %>
  </body>
<script src="../js/shopping/join.js?v=6"></script>
</html>