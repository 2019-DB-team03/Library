<%@ page import="managerMode.ManageMember" language="java" 
	contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<jsp:useBean id="manage_m" class="managerMode.ManageMember" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ�� ���� ������</title>
</head>
<body>
	<div style="line-height: 300%; font-size: 15px; ">
		<h3>ȸ�� ����</h3>
		<form method="post" action="manageMemberPro.jsp">
			<select name="searchType">
				<option value="">�з�</option>
				<option value="��ü" selected="selected">��ü</option>
				<option value="�кλ�">�кλ�</option>
				<option value="���п���">���п���</option>
				<option value="������">������</option>
			</select>
			<input type="text" name="search_content" maxlength="225">
			<input type="submit" value="�˻�" /> <br/>
		</form>	
			<%= manage_m.print_allMember_list() %>
			
			<input type="button" onclick="location.href='managerMain.jsp'" value="���">
		
	</div>
</body>
</html>