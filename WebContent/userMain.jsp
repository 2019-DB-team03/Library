<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>����� ���� ������</title>
</head>
<body>
	<h3>����� ��� ����</h3>
	<input type="button" onclick="location.href='userSearchBookForm.jsp'" value="���� �˻� &����&����"></br></br>
	<input type="button" onclick="location.href='email_addChooseRForm.jsp'" value="���"></br></br>
	<input type="button" onclick="location.href='email_addChooseRForm.jsp'" value="���"></br></br>
	<input type="button" id="myInfoBtn" value="�� ���� ����"></br></br>
</body>
<script>
document.getElementById("myInfoBtn").addEventListener("click",toJoinFun);
function toJoinFun(){
	var jbResult = prompt('��й�ȣ�� �Է��� �ּ���','');
	if(jbResult=="" || jbResult==null){
	}else{
		location.href="userInfoPage.jsp?pw="+jbResult;
	}	
}
</script>
</html> 