<%@ page import="userMode.UserAccount" language="java"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="uAccount" class="userMode.UserAccount" scope="page" />
<%
	request.setCharacterEncoding("utf-8");
%>

<%
	String id = request.getParameter("member_id");
	String result = uAccount.getInfo(id);
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
		location.href = 'leaveWithManager.jsp';
	}

	function updatePage() {
		location.href = 'update.jsp';
	}
</script>
</html>