<%@ page import="managerMode.ManagerSearchBook" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="mSearchBook" class="managerMode.ManagerSearchBook" scope="page" />
<%
	request.setCharacterEncoding("UTF-8");
%>

<%
	String title = request.getParameter("title");
	String isbn = request.getParameter("isbn");
	String author = request.getParameter("author");
	String publisher = request.getParameter("publisher");
	
	String result = mSearchBook.add_book(title, isbn, author, publisher);
	out.println("<script>alert('"+result+"');location.href='insertBookForm.jsp'</script>");
%> 