<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<title>JOIN</title>
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
	height: 300px;
	width: 250px;
	top: calc(40% - 100px);
	left: calc(50% - 125px);
}

#btnDiv {
	padding-top: 10px;
}

#btnDiv>input {
	margin-left: 5px;
	margin-right: 10px;
}

#inputDiv>input{
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
		<form class="centered" method="post" action="joinMemberPro.jsp">
			<div class="centered" id="inputDiv">
				<label for="name">NAME</label> <input type="text" name="name" id="name" required /><br />
				<label for="id">ID</label> <input type="text" name="id" id="id" required /><br />
				<label for="pw">PW</label> <input type="password" name="pw" id="pw" required /><br />
				<label for="email">E-MAIL</label> <input type="email" name="email" id="email" /><br />
				<label for="phone">PHONE</label> <input type="tel" name="phone" id="phone" /><br />
				<label for="category">CATEGORY</label>
				<select name="category" id="category">
					<option value="학부생" selected="selected">학부생</option>
					<option value="대학원생">대학원생</option>
					<option value="교직원">교직원</option>
					<option value="관리자">관리자</option>
				</select>
			</div>
			<div class="centered" id="btnDiv">
				<input type="button" id="cancelBtn" value="취소" />
				<input type="submit" value="회원가입" />
			</div>
		</form>
	</section>
</body>
<script>
	document.getElementById("cancelBtn").addEventListener("click", toBackFun);
	function toBackFun() {
		clearAll();
		window.history.back();
	}

	function clearAll() {
		var target = document.getElementById("inputDiv");
		for(var i=1;i<target.childElementCount;i+=3){
			target.children[i].value="";
		}
	}
</script>
</html>