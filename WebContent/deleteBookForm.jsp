<%@ page import="managerMode.ManagerSearchBook" language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:useBean id="mSearchBook" class="managerMode.ManagerSearchBook" scope="page" />
<%
	request.setCharacterEncoding("euc-kr");
%>

<%
	String bookID = request.getParameter("bookID");
	String result = mSearchBook.removeBook(bookID);
	
	out.println("<script>alert('"+result+"');location.href='managerSearchBookForm.jsp'</script>");
%>
