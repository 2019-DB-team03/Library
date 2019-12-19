package managerMode;

import dbManage.DBManage;

public class ManageMember {

static DBManage db = new DBManage();
	
	public static String print_allMember_list() {
		String str = db.select_allMember();
		
		if(str == null) {
			return "회원 목록이 비어있습니다.";
		}
		else {
			String[] memInfos = str.split("\\*");
			String ret = "";
			String name, member_id, category, total_loan;
			for(int i =0; i<memInfos.length; i++) {
				String[] memInfo = memInfos[i].split("\\|"); // 정보별로 split
	
				name = memInfo[0];
				member_id = memInfo[1];
				category = memInfo[2];
				total_loan = memInfo[3];
				
				if(i<10) {
					ret += "<tr style=\"color:blue; font-weight: bold;\">" 
							+ "<td>" + name + "</td>" 
							+ "<td>"+member_id + "</td>" 
							+ "<td>" + category + "</td>" + "<td>" + total_loan + "</td>"
							+ "<td> <form method=\"post\" action=\"updateMemberInfo.jsp\"><input type=\"hidden\" name=\"member_id\" value=\""+member_id+"\"><input type=\"submit\" value=\"정보수정/탈퇴\" /></form> <form method=\"post\" action=\"viewMemberLoanList.jsp\"><input type=\"hidden\" name=\"member_id\" value=\""+member_id+"\"><input type=\"submit\" value=\"대출기록관리\" /></form></td>"							
							+ "</tr>";
				}
				else {
					ret += "<tr>" 
							+ "<td>" + name + "</td>" 
							+ "<td>"+member_id + "</td>" 
							+ "<td>" + category + "</td>"+ "<td>" + total_loan + "</td>"
							+ "<td> <form method=\"post\" action=\"updateMemberInfo.jsp\"><input type=\"hidden\" name=\"member_id\" value=\""+member_id+"\"><input type=\"submit\" value=\"정보수정/탈퇴\" /></form> <form method=\"post\" action=\"viewMemberLoanList.jsp\"><input type=\"hidden\" name=\"member_id\" value=\""+member_id+"\"><input type=\"submit\" value=\"대출기록관리\" /></form></td>"							
							+ "</tr>";
				}
				

			}
			return "<table style=\"width: 600px; height:300px; display: block; overflow: auto;\" border='1px' cellspacing='0'><tr><th>회원 이름</th><th>회원 아이디</th><th>분류</th><th>대출권수</th><th></th></tr>"
			+ ret + "</table>";
		}
	}
	public static String print_SearchMember_list(String type, String content) {
		String str = db.select_searchMember(type, content);
		
		if(str == null) {
			return "해당 회원은 존재하지 않습니다.";
		}
		else {
			String[] memInfos = str.split("\\*");
			String ret = "";
			String name, member_id, total_loan;
			for(int i =0; i<memInfos.length; i++) {
				String[] memInfo = memInfos[i].split("\\|"); // 정보별로 split
	
				name = memInfo[0];
				member_id = memInfo[1];
				total_loan = memInfo[2];
				
				ret += "<tr>" 
						+ "<td>" + name + "</td>" 
						+ "<td>"+member_id + "</td>" 
						+ "<td>" + total_loan + "</td>"
						+ "<td> <form method=\"post\" action=\"updateMemberInfo.jsp\"><input type=\"hidden\" name=\"member_id\" value=\""+member_id+"\"><input type=\"submit\" value=\"정보수정/탈퇴\" /></form> <form method=\"post\" action=\"viewMemberLoanList.jsp\"><input type=\"hidden\" name=\"member_id\" value=\""+member_id+"\"><input type=\"submit\" value=\"대출기록관리\" /></form></td>"							
						+ "</tr>";
				

			}
			return "<table style=\"width: 600px; height:300px; display: block; overflow: auto;\" border='1px' cellspacing='0'><tr><th>회원 이름</th><th>회원 아이디</th><th>대출권수</th><th></th></tr>"
			+ ret + "</table>";
		}
	}
}
