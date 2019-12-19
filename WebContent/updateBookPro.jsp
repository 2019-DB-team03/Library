<%@ page import="managerMode.ManagerSearchBook" language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:useBean id="mSearchBook" class="managerMode.ManagerSearchBook" scope="page" />
<%
	request.setCharacterEncoding("euc-kr");
%>

<%
	String title = request.getParameter("title");
	String bookID = request.getParameter("bookID");
	String isbn = request.getParameter("isbn");
	String author = request.getParameter("author");
	String publisher = request.getParameter("publisher");

	String result = mSearchBook.updateBookInfo(bookID, title, isbn, author, publisher);
	
	out.println("<script>alert('"+result+"');location.href='managerSearchBookForm.jsp'</script>");
%>