<%@ page import="managerMode.ManagerSearchBook" language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:useBean id="mSearchBook" class="managerMode.ManagerSearchBook" scope="page" />
<%
	request.setCharacterEncoding("euc-kr");
%>

<%
	String bookID = request.getParameter("bookID");
	String bookInfo = mSearchBook.bookInfo(bookID);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="UTF-8">
<title>도서 수정 페이지</title>
<style>
    div {
        width: 100%;
        height: 300px;
        border: 1px solid #000;
    }
    div.left {
        width: 50%;
        float: left;
        box-sizing: border-box;
    }
    div.right {
        width: 50%;
        float: right;
        box-sizing: border-box;
    }
    </style>
</head>
<body>
	<h3>도서 수정</h3>
	<div style="line-height: 300%; font-size: 15px; font-weight: bold;">
		<form method="post" action="updateBookPro.jsp">
			<div class="left">
			<%= bookInfo%>
			</div>
			<div class="right">
			[변경]<br/>
			책 제목 : <input type="text" name="title" maxlength="40" required> <br />
			ISBN : <input type="text" name="isbn" maxlength="13" required> <br />
			저자 : <input type="text" name="author" maxlength="40" required> <br />
			출판사 : <input type="text" name="publisher" maxlength="40" required><br />
			</div>
			<input type="submit" value="수정" />
			<input type="button" onclick="location.href='managerSearchBookForm.jsp'" value="뒤로가기">
		</form>
	</div>
	<br />
</body>
</html>