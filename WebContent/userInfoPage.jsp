<%@ page import="userMode.UserAccount" language="java"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="uAccount" class="userMode.UserAccount" scope="page" />
<%
	request.setCharacterEncoding("utf-8");
%>

<%
	String password = request.getParameter("pw").trim();
	String result = uAccount.getCurrentUserData(password);
%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<title>INFO</title>
</head>
<body>
	<section>
		<form action="update.jsp" method="post">
	<%=result%>
	</form>
	</section>
</body>
<script>
	document.getElementById("leave").addEventListener("click", leavePage);
	document.getElementById("update").addEventListener("click", updatePage);
	function leavePage() {
		location.href = 'leave.jsp';
	}

	function updatePage() {
		location.href = 'update.jsp';
	}
</script>
</html>