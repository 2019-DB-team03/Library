<%@ page import="managerMode.ManagerSearchBook" language="java" 
	contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<jsp:useBean id="m_searchBook" class="managerMode.ManagerSearchBook" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ���� ���� ������</title>
</head>
<body>
	
	<div style="line-height: 300%; font-size: 15px; ">
		<h3>���� ���� ����</h3>
		<form method="post" action="managerSearchBookPro.jsp">
			<input type="button" onclick="location.href='insertBookForm.jsp'" value="�����߰��ϱ�"></br>
			<select name="searchType">
				<option value="">�з�</option>
				<option value="ISBN">ISBN</option>
				<option value="����">����</option>
			</select>
			<input type="text" name="search_content" maxlength="225">
			<input type="submit" value="�˻�" /> <br/>
			</form>
			<%= m_searchBook.print_allBook_list() %>

		
		
		<input type="button" onclick="location.href='managerSearchBookForm.jsp'" value="�ڷΰ���">
		<script>
     	
    	</script>
	</div>
</body>
</html>