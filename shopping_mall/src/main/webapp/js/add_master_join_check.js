function ajoin() {
	
	var aid = document.querySelector('input[name="aid"]').value;
    var apass = document.querySelector('input[name="apass"]').value;
    var passck = document.querySelector('input[name="passck"]').value;
    var aname = document.querySelector('input[name="aname"]').value;
    var aemail = document.querySelector('input[name="aemail"]').value;
    var atel = document.querySelectorAll('input[name="atel"]');
    var adept = document.querySelector('select[name="adept"]').value;
    var aposition = document.querySelector('select[name="aposition"]').value;
	
	
    //빈칸 확인
	if(aid=="" || apass=="" || aname=="" || aemail=="" || atel==""|| adept=="" || aposition==""){
		alert("모든 정보란을 입력해주세요!");
	}
    //비밀번호 체크
	else if(apass != passck){
		alert("패스워드와 패스워드 확인란이 일치하지 않습니다!!")
	}
    //폼보내기
	else{
		join_frm.method = "post";
    	join_frm.action = "./ajoinok.do";
    	join_frm.submit();
	}
}

function joinreset(){
	location.href="./index.do"
}


//아이디 중복체크
function checkId() {
    var aid = document.querySelector('input[name="aid"]').value;
    var checkResultSpan = document.getElementById("checkidResult");

    $.ajax({
        url: '/checkId.do',
        type: 'POST',
        data: { aid: aid },
        success: function(response) {
            if (response.trim() === "사용 가능한 아이디입니다!") {
                checkResultSpan.innerHTML = "<font color='blue'>사용 가능</font>";
            } else if (response.trim() === "중복된 아이디입니다.") {
                checkResultSpan.innerHTML = "<font color='red'>중복된 아이디입니다</font>";
            } else {
                checkResultSpan.innerHTML = "<font color='red'>오류 발생</font>";
            }
        },
        error: function(xhr, status, error) {
            console.error('Error:', error);
            checkResultSpan.innerHTML = "<font color='red'>서버와의 통신 오류 발생</font>";
        }
    });
}