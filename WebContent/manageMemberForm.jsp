<%@ page import="managerMode.ManageMember" language="java" 
	contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<jsp:useBean id="manage_m" class="managerMode.ManageMember" scope="page" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원 관리 페이지</title>
</head>
<body>
	<div style="line-height: 300%; font-size: 15px; ">
		<h3>회원 관리</h3>
		<form method="post" action="manageMemberPro.jsp">
			<select name="searchType">
				<option value="">분류</option>
				<option value="전체" selected="selected">전체</option>
				<option value="학부생">학부생</option>
				<option value="대학원생">대학원생</option>
				<option value="교직원">교직원</option>
			</select>
			<input type="text" name="search_content" maxlength="225">
			<input type="submit" value="검색" /> <br/>
		</form>	
			<%= manage_m.print_allMember_list() %>
			
			<input type="button" onclick="location.href='managerMain.jsp'" value="취소">
		
	</div>
</body>
</html>