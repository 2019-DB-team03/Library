<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>사용자 메인 페이지</title>
</head>
<body>
	<h3>사용자 기능 선택</h3>
	<input type="button" onclick="location.href='userSearchBookForm.jsp'" value="도서 검색 &예약&대출"></br></br>
	<input type="button" onclick="location.href='email_addChooseRForm.jsp'" value="취소"></br></br>
	<input type="button" onclick="location.href='email_addChooseRForm.jsp'" value="취소"></br></br>
	<input type="button" id="myInfoBtn" value="내 정보 관리"></br></br>
</body>
<script>
document.getElementById("myInfoBtn").addEventListener("click",toJoinFun);
function toJoinFun(){
	var jbResult = prompt('비밀번호를 입력해 주세요','');
	if(jbResult=="" || jbResult==null){
	}else{
		location.href="userInfoPage.jsp?pw="+jbResult;
	}	
}
</script>
</html> 