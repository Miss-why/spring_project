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


//ID중복체크
$(function() {
	$("#btn5").click(function() {
		if ($("#aid").val() == "" || $("#aid").val() == null) {
			alert("중복확인할 아이디를 입력해주세요.")
		}
		else {
			$.ajax({
				url: "/admin/idcheck.do",
				type: "post",
				dataType: "text",
				data: {
					aid: $("#aid").val()
				},
				contentType: "application/x-www-form-urlencoded",
				success: function($result) {
					//console.log($result)
					if ($result == "ok") {
						alert("사용 가능한 아이디입니다.")
					}
					else if ($result == "no") {
						alert("중복된 아이디입니다. 다른 아이디를 사용해주세요!!")
					}
					else {
						alert("아이디 중복체크 중 error 발생!!")
					}
				},
				error: function() {
					alert("통신오류발생으로 인해 아이디 중복체크되지 않습니다.");
				}
			});
		}
	});
});
