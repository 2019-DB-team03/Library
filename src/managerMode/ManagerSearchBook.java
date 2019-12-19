package managerMode;

import dbManage.DBManage;

public class ManagerSearchBook {

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
				
				ret += "<tr>" + "<td>" + title + "</td>" + "<td>" + isbn + "</td>" 
						+ "<td>" + author + "</td>" + "<td>" + publisher + "</td>" 
						+ "<td>" + book_id + "</td>" 
						+ "<td> <form method=\"post\" action=\"deleteBookForm.jsp\"><input type=\"hidden\" name=\"bookID\" value=\""+book_id+"\"><input type=\"submit\" value=\"삭제\" /></form> <form method=\"post\" action=\"updateBookForm.jsp\"><input type=\"hidden\" name=\"bookID\" value=\""+book_id+"\"><input type=\"submit\" value=\"수정\" /></form></td>"						
						+ "</tr>";

			}
			return "<table style=\"width: 600px; height:300px; display: block; overflow: auto;\" border='1px' cellspacing='0'><tr><th>책제목</th><th>ISBN</th><th>저자</th><th>출판사</th><th>책번호</th><th></th></tr>"
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
	
				title = bookInfo[0];
				isbn = bookInfo[1];
				author = bookInfo[2];
				publisher = bookInfo[3];
				book_id = bookInfo[4];
				isavailable = bookInfo[5];
				
				ret += "<tr>" + "<td>" + title + "</td>" + "<td>" + isbn + "</td>" 
						+ "<td>" + author + "</td>" + "<td>" + publisher + "</td>" 
						+ "<td>" + book_id + "</td>" 
						+ "<td> <form method=\"post\" action=\"deleteBookForm.jsp\"><input type=\"hidden\" name=\"bookID\" value=\""+book_id+"\"><input type=\"submit\" value=\"삭제\" /></form> <form method=\"post\" action=\"updateBookForm.jsp\"><input type=\"hidden\" name=\"bookID\" value=\""+book_id+"\"><input type=\"submit\" value=\"수정\" /></form></td>"						
						+ "</tr>";
			}
			return "<table style=\"width: 600px; height:300px; display: block; overflow: auto;\" border='1px' cellspacing='0'><tr><th>책제목</th><th>ISBN</th><th>저자</th><th>출판사</th><th>책번호</th><th></th></tr>"
			+ ret + "</table>";
		}
	}
	public static String removeBook(String book_id) {
		String str = db.delete_book(Integer.parseInt(book_id));
		if(str== null) {
			return "해당 도서 삭제를 실패하였습니다.";
		}
		else {
			return "해당 도서 삭제를 성공하였습니다.";
		}
	}

	public static String add_book(String title, String isbn, String author, String publisher) {
		String str = db.insert_book(title, isbn, author, publisher);
		return str;
	}
	
	public static String bookInfo(String book_id) {
		String str = db.select_book(Integer.parseInt(book_id));
		if(str == null) {
			return "도서번호 "+book_id+"는 본 도서관에 없습니다.";
		}
		else {
			String[] bookInfos = str.split("\\*");
			String ret = "";
			String title, isbn, author, publisher, isavailable;
			for(int i =0; i<bookInfos.length; i++) {
				String[] bookInfo = bookInfos[i].split("\\|"); // 정보별로 split
	
				title = bookInfo[0];
				isbn = bookInfo[1];
				author = bookInfo[2];
				publisher = bookInfo[3];
				book_id = bookInfo[4];
				isavailable = bookInfo[5];
				
		//		ret += "[현재]<br/>책 제목 : "+title+"<br/>ISBN : "+isbn+"<br/>저자 : "+author+"<br/>출판사 : "+publisher+"<br/>도서 번호 : <input type=\"text\" name=\"bookID\" value=\""+book_id+"\" readonly><br/>";
				ret += "[현재]<br/>책 제목 : <input type=\"text\" name=\"a\" value=\""+title+"\" readonly><br/>"
								+"ISBN : <input type=\"text\" name=\"a\" value=\""+isbn+"\" readonly><br/>"
								+"저자 : <input type=\"text\" name=\"a\" value=\""+author+"\" readonly><br/>"
								+"출판사 : <input type=\"text\" name=\"a\" value=\""+publisher+"\" readonly><br/>"
								+"도서 번호 : <input type=\"text\" name=\"bookID\" value=\""+book_id+"\" readonly><br/>";
			}
			return ret;
		}
	}
	
	public static String updateBookInfo(String book_id, String title, String isbn, String author, String publisher) {
		if(db.update_book(Integer.parseInt(book_id), title, isbn, author, publisher)) {
			return "성공적으로 정보를 업데이트하였습니다.";
		}
		return "정보 업데이트에 실패하였습니다.";
	}
}
