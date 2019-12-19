<%@ page import="userMode.UserAccount" language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<jsp:useBean id="uAccount" class="userMode.UserAccount" scope="page" />
<%
	request.setCharacterEncoding("utf-8");
%>

<%
	String searchName = request.getParameter("name");
	String searchId = request.getParameter("id");
	String searchPw = request.getParameter("pw");
	String searchEmail = request.getParameter("email");
	String searchPhone = request.getParameter("phone");
	String searchCategory = request.getParameter("category");
	String result = uAccount.getJoinState(searchName, searchId, searchPw, searchEmail, searchPhone, searchCategory);
%>
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
	width: 300px;
	top: calc(40% - 100px);
	left: calc(50% - 150px);
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
		<%= result %>
	</section>
</body>
</html>
