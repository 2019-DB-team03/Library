<%@ page import="userMode.UserAccount" language="java"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="uAccount" class="userMode.UserAccount" scope="page" />
<%
	request.setCharacterEncoding("utf-8");
%>

<%
String id = request.getParameter("id");
	String result = uAccount.leaveUser(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LEAVE</title>
</head>
<body>
	<section>
		<%=result%>
	</section>
</body>
</html>