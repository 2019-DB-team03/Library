<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<title>Main</title>
<style type="text/css">
html, body {
	width: 100%;
	height: 100%;
	padding: 0px;
	margin: 0px;
}

.centered {
	display: table;
	margin-left: auto;
	margin-right: auto;
}

section {
	position: absolute;
	height: 150px;
	width: 200px;
	top : calc(40% - 75px);
	left: calc(50% - 100px);
}

#btnDiv {
	padding-top: 10px;
}

#btnDiv>input {
	margin-left: 5px;
	margin-right: 10px;
}

#inputDiv>input {
	position: absolute;
	right: 0;
}

#inputDiv>label {
	position: absolute;
	left: 0;
}
</style>

</head>

<body>
	<section>
		<h1 class="centered">로그인</h1>
		<form class="centered" method="post" action="login.jsp">
			<div class="centered" id="inputDiv">
				<label for="id">ID </label> <input type="text" name="id" id="id" /><br />
				<label for="pw">PW </label> <input type="password" name="pw" id="pw" /><br />
			</div>
			<div class="centered" id="btnDiv">
				<input type="button" id="joinBtn" value="회원가입" "/>
				<input type="submit" value="로그인" />
			</div>
		</form>
	</section>
</body>
<script>
	document.getElementById("joinBtn").addEventListener("click",toJoinFun);
	function toJoinFun(){
		clearAll();
		location.href="joinMemberForm.jsp";
	}
	
	function clearAll(){
		document.getElementById("id").value="";
		document.getElementById("pw").value="";
	}
</script>
</html>