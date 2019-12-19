<%@ page import="userMode.UserSearchBook" language="java"
   contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<jsp:useBean id="uSearchBook" class="userMode.UserSearchBook"
   scope="page" />
<%
   request.setCharacterEncoding("utf-8");
%>

<%
   String searchType = request.getParameter("searchType");
   String searchContent = request.getParameter("search_content");
   String result = uSearchBook.print_SearchBook_list(searchType, searchContent);
%>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="utf-8">
<title>Main</title>
<style type="text/css">
html, body {
   width: 100%;
   height: 100%;
   padding: 0px;
   margin: 0px;
}

.centered {
   display: table;
   margin-left: auto;
   margin-right: auto;
}

section {
   position: absolute;
   height: 150px;
   width: 200px;
   top : calc(40% - 75px);
   left: calc(50% - 100px);
}

#btnDiv {
   padding-top: 10px;
}

#btnDiv>input {
   margin-left: 5px;
   margin-right: 10px;
}

#inputDiv>input {
   position: absolute;
   right: 0;
}

#inputDiv>label {
   position: absolute;
   left: 0;
}
</style>

</head>

<body>
   <section>
      <h1 class="centered">로그인</h1>
      <form class="centered" method="post" action="login.jsp">
         <div class="centered" id="inputDiv">
            <label for="id">ID </label> <input type="text" id="id" /><br />
            <label for="pw">PW </label> <input type="password" id="pw" /><br />
         </div>
         <div class="centered" id="btnDiv">
            <input type="button" id="joinBtn" value="회원가입" "/>
            <input type="submit" value="로그인" />
         </div>
      </form>
   </section>
</body>
<script>
   document.getElementById("joinBtn").addEventListener("click",function(){
      toJoinFun(2);
   }); 
   function toJoinFun(num){
      location.href="joinMemberForm.jsp";
      clearAll();
   }
   
   function clearAll(){
      document.getElementById("id").value="";
      document.getElementById("pw").value="";
   }
   
   function pageMove(){
	      console.log("<%= result %>");
   }
</script>
</html>