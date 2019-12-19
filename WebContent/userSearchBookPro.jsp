<%@ page import="userMode.UserSearchBook" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="uSearchBook" class="userMode.UserSearchBook" scope="page" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<%
	String searchType = request.getParameter("searchType");
	String searchContent = request.getParameter("search_content");
	String result = uSearchBook.print_SearchBook_list(searchType, searchContent);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>검색결과 및 대출 예약 페이지</title>
</head>
<body>
	<div>분류 : <%= searchType %></div> 
	<div>입력한 검색어 : <%= searchContent %></div>
	
	<%= result %></br>
	<input type="button" onclick="location.href='userSearchBookForm.jsp'" value="뒤로가기">
</body>
</html>