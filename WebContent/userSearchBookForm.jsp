<%@ page import="userMode.UserSearchBook" language="java" 
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="u_searchBook" class="userMode.UserSearchBook" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서검색 및 대출 예약 페이지</title>
</head>
<body>
	
	<div style="line-height: 300%; font-size: 15px; ">
		<h3>도서 검색</h3>
		<form method="post" action="userSearchBookPro.jsp">
			<select name="searchType">
				<option value="">분류</option>
				<option value="ISBN">ISBN</option>
				<option value="제목">제목</option>
			</select>
			<input type="text" name="search_content" maxlength="225">
			<input type="submit" value="검색" /> <br/>
		</form>
			<%= u_searchBook.print_allBook_list() %>
			
			<input type="button" onclick="location.href='userMain.jsp'" value="뒤로가기">

	</div> 
</body>
</html>