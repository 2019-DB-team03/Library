package userMode;

import dbManage.DBManage;

public class UserSearchBook {
	static DBManage db = new DBManage();
	
	public static String print_allBook_list() {
		String str = db.select_allBook();
		if(str == null) {
			return "도서 목록이 비어있습니다.";
		}
		else {
			String[] bookInfos = str.split("\\*");
			String ret = "";
			String title, isbn, author, publisher, book_id, isavailable;
			for(int i =0; i<bookInfos.length; i++) {
				String[] bookInfo = bookInfos[i].split("\\|"); // 정보별로 split

				title = bookInfo[0];
				isbn = bookInfo[1];
				author = bookInfo[2];
				publisher = bookInfo[3];
				book_id = bookInfo[4];
				isavailable = bookInfo[5];
				
				String currentUser = db.select_currentUser();
				boolean isReservation = db.select_isReserve(currentUser, Integer.parseInt(book_id));
				boolean isLoan = db.select_isLoan(currentUser, Integer.parseInt(book_id));
				
				if(isReservation || isLoan) {
					ret += "<tr>" + "<td>" + title + "</td>" + "<td>" + isbn + "</td>" 
							+ "<td>" + author + "</td>" + "<td>" + publisher + "</td>" 
							+ "<td>"+book_id + "</td>" 
							+ "<td></td>"
							+ "</tr>";
				}
				else {
					if(isavailable.equals("t")) {
						ret += "<tr>" 
								+ "<td>" + title + "</td>" + "<td>" + isbn + "</td>" 
								+ "<td>" + author + "</td>" + "<td>" + publisher + "</td>" 
								+ "<td>"+book_id + "</td>" 
								+ "<td> <form method=\"post\" action=\"userMain.jsp\"><input type=\"hidden\" name=\"book_id\" value=\""+book_id+"\"><input type=\"submit\" value=\"대출\" /></form> </td>"
								+ "</tr>";
					}
					else {
						ret += "<tr>" 
								+ "<td>" + title + "</td>" + "<td>" + isbn + "</td>" 
								+ "<td>" + author + "</td>" + "<td>" + publisher + "</td>" 
								+ "<td><input type=\"text\" name=\"book_id\" value=\""+book_id+"\" readonly></td>"
								+ "<td> <form method=\"post\" action=\"userMain.jsp\"><input type=\"hidden\" name=\"book_id\" value=\""+book_id+"\"><input type=\"submit\" value=\"예약\" /></form> </td>"
								+ "</tr>";
					}
				}
			}
			return "<table style=\"width: 600px; height:300px; display: block; overflow: auto;\" border='1px' cellspacing='0'><tr><th>책제목</th><th>ISBN</th><th>저자</th><th>출판사</th><th>책번호</th><th>예약/대여</th></tr>"
			+ ret + "</table>";
		}
	}

	public static String print_SearchBook_list(String type, String content) {
		String str = db.select_searchBook(type, content);
		if(str == null) {
			return "해당 도서는 본 도서관에 없습니다.";
		}
		else {
			String[] bookInfos = str.split("\\*");
			String ret = "";
			String title, isbn, author, publisher, book_id, isavailable;
			for(int i =0; i<bookInfos.length; i++) {
				String[] bookInfo = bookInfos[i].split("\\|"); // 정보별로 split
				int n = bookInfo.length;
				title = bookInfo[0];
				isbn = bookInfo[1];
				author = bookInfo[2];
				publisher = bookInfo[3];
				book_id = bookInfo[4];
				isavailable = bookInfo[5];
				
				String currentUser = db.select_currentUser();
				boolean isReservation = db.select_isReserve(currentUser, Integer.parseInt(book_id));
				boolean isLoan = db.select_isLoan(currentUser, Integer.parseInt(book_id));
				
				if(isReservation || isLoan) {
					ret += "<tr>" + "<td>" + title + "</td>" + "<td>" + isbn + "</td>" 
							+ "<td>" + author + "</td>" + "<td>" + publisher + "</td>" 
							+ "<td>"+book_id + "</td>" 
							+ "<td></td>"
							+ "</tr>";
				}
				else {
					if(isavailable.equals("t")) {
						ret += "<tr>" 
								+ "<td>" + title + "</td>" + "<td>" + isbn + "</td>" 
								+ "<td>" + author + "</td>" + "<td>" + publisher + "</td>" 
								+ "<td>"+book_id + "</td>" 
								+ "<td> <form method=\"post\" action=\"userMain.jsp\"><input type=\"hidden\" name=\"book_id\" value=\""+book_id+"\"><input type=\"submit\" value=\"대출\" /></form> </td>"
								+ "</tr>";
					}
					else {
						ret += "<tr>" 
								+ "<td>" + title + "</td>" + "<td>" + isbn + "</td>" 
								+ "<td>" + author + "</td>" + "<td>" + publisher + "</td>" 
								+ "<td><input type=\"text\" name=\"book_id\" value=\""+book_id+"\" readonly></td>"
								+ "<td> <form method=\"post\" action=\"userMain.jsp\"><input type=\"hidden\" name=\"book_id\" value=\""+book_id+"\"><input type=\"submit\" value=\"예약\" /></form> </td>"
								+ "</tr>";
					}
				}
			}
			return "<table style=\"width: 600px; height:300px; display: block; overflow: auto;\" border='1px' cellspacing='0'><tr><th>책제목</th><th>ISBN</th><th>저자</th><th>출판사</th><th>책번호</th><th>예약/대여</th></tr>"
			+ ret + "</table>";
		}
	}
}
