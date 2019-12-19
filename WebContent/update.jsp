<%@ page import="userMode.UserAccount" language="java"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="uAccount" class="userMode.UserAccount" scope="page" />
<%
	request.setCharacterEncoding("utf-8");
%>

<%
	String searchId = request.getParameter("id");
	String searchPw = request.getParameter("pw");
	String searchName = request.getParameter("name");
	String searchEmail = request.getParameter("email");
	String searchPhone = request.getParameter("phone");
	String searchCategory = request.getParameter("category");
	String result = uAccount.updateUser(searchId,searchPw,searchName,searchEmail,searchPhone,searchCategory);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UPDATE</title>
</head>
<body>
	<section>
	<%=result%>
	</section>
</body>
</html>