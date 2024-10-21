<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<footer>
    <div class="footer">
      <a href="http://facebook.com">
        <img src="../images/facebook.png"height="20">
      </a>
      <a href="http://instagram.com">
        <img src="../images/instagram.png"height="20">
      </a>
      <a href="http://twitter.com">
        <img src="../images/twitter.png"height="20">
      </a>
    </div>
    <section class="foot_section"></section>
    <aside class="aside_footer">
        <div class="div_footer">
        <ul>
        <li><img src="../images/foot_logo.png"></li>
        <li>
회사명 : ${ hpInfo.getCompamy_name() }
대표자 : ${ hpInfo.getPresident_name() } | ${ hpInfo.getPresident_phone() }
주소 : (${ hpInfo.getBusiness_zipcode() }) ${ hpInfo.getBusiness_address() } <br>
<a href="#">고객센터</a> | 상담시간 : 상담시간 : (10:00 ~ 19:00까지) 단, 12:00 ~ 13:00 점심시간
E-Mail : ${ hpInfo.getAdminemail() }
사업자등록번호 : ${ hpInfo.getBusiness_regist_number() } <br>
통신판매업신고번호 : ${ hpInfo.getMailorder_report_number() }
개인정보보호책임자 : ${ hpInfo.getInfomanager_name() } | ${ hpInfo.getInfomanager_email() } <br>
Copyright © 도메인명 All Rights Reserved.
        </li>
        </ul>    
        </div>
    </aside>
</footer>