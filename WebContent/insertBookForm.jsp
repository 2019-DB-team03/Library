<%@ page language="java" 
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서 추가 페이지</title>
</head>
<body>
	<h3>도서 추가</h3>
	<div style="line-height: 300%; font-size: 15px; font-weight: bold;">
		<form method="post" action="insertBookPro.jsp">
			책 제목 : <input type="text" name="title" maxlength="50" required> <br />
			ISBN : <input type="text" name="isbn" maxlength="13" required> <br />
			저자 : <input type="text" name="author" maxlength="40" required> <br />
			출판사 : <input type="text" name="publisher" maxlength="40" required><br />
	
			<input type="submit" value="추가" /> 
			<input type="button" onclick="location.href='managerSearchBookForm.jsp'" value="뒤로가기">
		</form>
	</div>
	<br />
</body>
</html>