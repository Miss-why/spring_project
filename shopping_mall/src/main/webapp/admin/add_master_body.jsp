<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form id="join_frm">
    <section class="admin_bgcolor_add">
        <div class="admin_login_add">
            <ul>
                <li class="font_color1">아이디 및 패스워드 정보</li>
                <li>
                <input type="text" name=aid class="add_input1" placeholder="생성할 관리자 아이디를 입력하세요">
                <button type="button" class="btn_button">중복체크</button>
                </li>
                <li>
                    <input type="password" name=apass class="add_input1" placeholder="접속할 패스워드를 입력하세요">
                    <input type="password" name=passck class="add_input1" placeholder="동일한 패스워드를 입력하세요">
                </li>
                <li class="font_color1">관리자 기본정보 입력</li>
                <li>
                    <input type="text" name=aname class="add_input1" placeholder="담당자 이름을 입력하세요">
                </li>
                <li>
                <input type="text" name=aemail class="add_input1 emails" placeholder="담당자 이메일을 입력하세요">
                </li>
                <li class="font_color1">
                <input type="text" name=tel class="add_input1 hp1" placeholder="HP" value="010" maxlength="3">
                -
                <input type="text" name=tel class="add_input1 hp2" placeholder="1234" maxlength="4">
                -
                <input type="text" name=tel class="add_input1 hp2" placeholder="5678" maxlength="4">
                </li>
                <li class="font_color1">관리자 담당부서 및 직책</li>
                <li>
                    <select class="add_select1" name=adept>
                        <option>담당자 부서를 선택하세요</option>
                        <option>임원</option>
                        <option>전산팀</option>
                        <option>디자인팀</option>
                        <option>CS팀</option>
                        <option>MD팀</option>
                    </select>
                    <select class="add_select1" name=aposition>
                        <option>담당자 직책을 선택하세요</option>
                        <option>대표</option>
                        <option>부장</option>
                        <option>팀장</option>
                        <option>과장</option>
                        <option>주임</option>
                        <option>대리</option>
                        <option>사원</option>
                    </select>
                </li>
                <li class="font_color1">※ 가입완료 후 전산 담당자가 확인 후 로그인 할 수 있습니다.</li>
            </ul>
            <span class="admin_addbtn">
                <button type="button" class="btn_button btncolor1" title="관리자 등록" onclick="ajoin()">관리자 등록</button>
                <button type="button" class="btn_button btncolor2" title="관리자 취소" onclick="joinreset()">등록 취소</button>
            </span>
        </div>
    </section>
</form>
</body>
<script>
function ajoin() {
	
	var aid = document.querySelector('input[name="aid"]').value;
    var apass = document.querySelector('input[name="apass"]').value;
    var passck = document.querySelector('input[name="passck"]').value;
    var aname = document.querySelector('input[name="aname"]').value;
    var aemail = document.querySelector('input[name="aemail"]').value;
    var tel = document.querySelectorAll('input[name="tel"]');
    var adept = document.querySelector('select[name="adept"]').value;
    var aposition = document.querySelector('select[name="aposition"]').value;
	
    //빈칸 확인
	if(aid=="" || apass=="" || aname=="" || aemail=="" || tel==""|| adept=="" || aposition==""){
		alert("모든 정보란을 입력해주세요!");		
	}
    //비밀번호 체크
	else if(apass != passck){
		alert("패스워드와 패스워드 확인란이 일치하지 않습니다!!")
	}
    
    //폼보내기
	else{
	// 전화번호 합치기
	var atel1 = tel[0].value;
	var atel2 = tel[1].value;
	var atel3 = tel[2].value;
	var fullatel = atel1 + atel2 + atel3;
	
    // 숨겨진 필드에 전화번호 저장
    var hiddenatel = document.createElement('input');
    hiddenatel.type = 'hidden';
    hiddenatel.name = 'atel';
    hiddenatel.value = fullatel;
    document.getElementById('join_frm').appendChild(hiddenatel);

    document.getElementById('join_frm').method = 'post';
    document.getElementById('join_frm').action = './ajoinok.do';
    document.getElementById('join_frm').submit();
	}
}
function joinreset(){
	location.href="./add_master.do"
}

</script>
</html>