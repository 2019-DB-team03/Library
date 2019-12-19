<%@ page import="managerMode.ManagerSearchBook" language="java" 
	contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<jsp:useBean id="m_searchBook" class="managerMode.ManagerSearchBook" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>도서 정보 관리 페이지</title>
</head>
<body>
	
	<div style="line-height: 300%; font-size: 15px; ">
		<h3>도서 정보 관리</h3>
		<form method="post" action="managerSearchBookPro.jsp">
			<input type="button" onclick="location.href='insertBookForm.jsp'" value="도서추가하기"></br>
			<select name="searchType">
				<option value="">분류</option>
				<option value="ISBN">ISBN</option>
				<option value="제목">제목</option>
			</select>
			<input type="text" name="search_content" maxlength="225">
			<input type="submit" value="검색" /> <br/>
			</form>
			<%= m_searchBook.print_allBook_list() %>

		
		
		<input type="button" onclick="location.href='managerSearchBookForm.jsp'" value="뒤로가기">
		<script>
     	
    	</script>
	</div>
</body>
</html>