<%@ page import="managerMode.ManagerSearchBook" language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:useBean id="mSearchBook" class="managerMode.ManagerSearchBook" scope="page" />
<%
	request.setCharacterEncoding("euc-kr");
%>

<%
	String searchType = request.getParameter("searchType");
	String searchContent = request.getParameter("search_content");
	String result = mSearchBook.print_SearchBook_list(searchType, searchContent);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>�˻���� �� ���� ������</title>
</head>
<body>
	<div>�з� : <%= searchType %></div>
	<div>�Է��� �˻��� : <%= searchContent %></div>
	
	<%= result %></br>

	<input type="button" onclick="location.href='managerSearchBookForm.jsp'" value="�ڷΰ���">
</body>
</html>