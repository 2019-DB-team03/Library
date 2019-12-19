package dbManage;

import java.sql.*;

public class DBManage {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	// DB 연결
	private boolean dbConnect() {
		try {
			String jdbcUrl = "jdbc:mysql://localhost:3305/db_library";
			String dbId = "root";
			String dbPass = "1234";

			Class.forName("com.mysql.jdbc.Driver");
			this.conn = DriverManager.getConnection(jdbcUrl, dbId, dbPass);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
		return true;
	}

	// DB 연결 닫기
	private void dbClose() {
		if (this.pstmt != null) {
			try {
				this.pstmt.close();
			} catch (SQLException sqle) {
			}
		}
		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException sqle) {

			}
		}
	}
	
	// id와 pw입력을 검사하고 로그인 처리를 해주는 메소드
	public String checkIdPw(String id, String pw) {
		String result = "";
		if (dbConnect()) {
			try {
				ResultSet rs = null;
				String sql = "select * from db_library.member where member_id = ?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					if (rs.getString("member_id").equals(id)) {
						if (rs.getString("password").equals(pw)) { // id, pw둘다 일치하는 것 찾음
							result += rs.getString("category");

							// id, pw가 일치할 경우 current에도 추가해줌
							sql = "select * from db_library.current";
							pstmt = this.conn.prepareStatement(sql);
							rs = pstmt.executeQuery();
							if (rs.next()) { // current에 값이 있는지를 확인하고 있을땐 업데이트 없을땐 insert
								sql = "update db_library.current set member_id='" + id + "' where member_id='"
										+ rs.getString("member_id") + "';";
								pstmt = this.conn.prepareStatement(sql);
								pstmt.executeUpdate();
							} else {
								sql = "insert into db_library.current values ('" + id + "');";
								pstmt = this.conn.prepareStatement(sql);
								pstmt.executeUpdate();
							}
							break;
						} else {
							result += "pw";
						}
					} else {
						result += "id";
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				result = "Connection Error";
			}
		}
		dbClose();
		return result;
	}// YAM

	// 회원가입을 통해 입력받은 값들을 db에 추가하는 메소드
	public String checkJoinVal(String name, String id, String pw, String email, String phone, String category) {
		String result = "";
		if (dbConnect()) {
			try {
				ResultSet rs = null;
				String sql = "select * from db_library.member where member_id = ?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					if (rs.getString("member_id").equals(id)) {
						result = "same_id_exists";
						break;
					}
				} // 같은 아이디가 존재하는지 확인함
				if (!result.equals("same_id_exists")) {
					// 추가 진행
					sql = "insert into db_library.member values ('" + id + "','" + pw + "','" + name + "','" + email
							+ "','" + phone + "','" + category + "',0);";
					pstmt = this.conn.prepareStatement(sql);
					pstmt.executeUpdate();
					result = "ok";
				}
			} catch (SQLException e) {
				e.printStackTrace();
				result = "Connection Error";
			}
		}
		dbClose();
		return result;
	}// YAM



	//select
	
	//모든 책 목록 
	
	public String select_allBook() {
		String result = null;
		if(dbConnect()) {
			try {
				String sql="";
				ResultSet rs = null;
				//책제목 , isbn, 저자, 출판사, 책번호, 예약/대여
				sql = "select * from db_library.book order by binary(title)";
				pstmt = this.conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = rs.getString("title") + "|" + rs.getString("isbn") + "|" + rs.getString("author")
								+ "|" + rs.getString("publisher")+ "|" + rs.getString("book_id")
								+ "|" + rs.getString("isavailable") + "*";
				}
				while (rs.next()) {
					result += rs.getString("title") + "|" + rs.getString("isbn") + "|" + rs.getString("author")
								+ "|" + rs.getString("publisher")+ "|" + rs.getString("book_id")
								+ "|" + rs.getString("isavailable") + "*";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		dbClose();
		return result;
	}
	
	//책 유형과 입력받은 내용에 따른 책 목록
	public String select_searchBook(String type, String content) {
		String result = null;
		if (dbConnect()) {
			try {
				String sql = "";
				ResultSet rs = null;
	
				if(type.equals("ISBN")) {
					sql = "select * from db_library.book where isbn = ? order by binary(title)";
					pstmt = this.conn.prepareStatement(sql);
					pstmt.setString(1, content);
					
					rs = pstmt.executeQuery();
					
					if (rs.next()) {
						result = rs.getString("title") + "|" + rs.getString("isbn") + "|" + rs.getString("author") +"|"
								+ rs.getString("publisher") + "|" + rs.getString("book_id") + "|" + rs.getString("isavailable")
								+ "*";
					}
					while (rs.next()) {
						result += rs.getString("title") + "|" + rs.getString("isbn") + "|" + rs.getString("author") +"|"
								+ rs.getString("publisher") + "|" + rs.getString("book_id") + "|" + rs.getString("isavailable")
								+ "*";
					}
				}
				else if(type.equals("제목")) {
					sql = "select * from db_library.book where title = ? order by binary(title)";
					
					pstmt = this.conn.prepareStatement(sql);
					pstmt.setString(1, content);
					
					rs = pstmt.executeQuery();
					if (rs.next()) {
						result = rs.getString("title") + "|" + rs.getString("isbn") + "|" + rs.getString("author")  +"|"
								+ rs.getString("publisher") + "|" + rs.getString("book_id") + "|" + rs.getString("isavailable")
								+ "*";
					}
					while (rs.next()) {
						result += rs.getString("title") + "|" + rs.getString("isbn") + "|" + rs.getString("author")  +"|"
								+ rs.getString("publisher") + "|" + rs.getString("book_id") + "|" + rs.getString("isavailable")
								+ "*";
					}
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		dbClose();
		return result;
	}
	
	public String select_allMember() {
		String result = null;
		if (dbConnect()) {
			try {
				String sql="";
				ResultSet rs = null;
				//이름, 회원 아이디, 대출권수
				sql = "select * from db_library.member order by total_loan desc";
				pstmt = this.conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = rs.getString("name") + "|" + rs.getString("member_id") + "|" + rs.getString("category") + "|" + rs.getString("total_loan")
								+ "*";
				}
				while (rs.next()) {
					result += rs.getString("name") + "|" + rs.getString("member_id") + "|" + rs.getString("category") + "|" + rs.getString("total_loan")
								+ "*";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		dbClose();
		return result;
	}
	public String select_searchMember(String type, String content) {
		String result = null;
		if (dbConnect()) {
			try {
				String sql = "";
				ResultSet rs = null;
				if(content.equals("") == false) {	//이름 + 부류 둘 다 입력
					if(type.equals("학부생")) {
						sql = "select * from db_library.member where category = ? and name = ? order by total_loan desc";
						pstmt = this.conn.prepareStatement(sql);
						pstmt.setString(1, type);
						pstmt.setString(2, content);
					}
					else if(type.equals("대학원생")) {
						sql = "select * from db_library.member where category = ? and name = ? order by total_loan desc";
						pstmt = this.conn.prepareStatement(sql);
						pstmt.setString(1, type);
						pstmt.setString(2, content);
					}
					else if(type.equals("교직원")) {
						sql = "select * from db_library.member where category = ? and name = ? order by total_loan desc";
						pstmt = this.conn.prepareStatement(sql);
						pstmt.setString(1, type);
						pstmt.setString(2, content);
					}
					else {	//전체 중에서 사람 이름만으로 검색
						sql = "select * from db_library.member where name = ? order by total_loan desc";
						pstmt = this.conn.prepareStatement(sql);
						pstmt.setString(1, content);
					}

					rs = pstmt.executeQuery();
					
					if (rs.next()) {
						result = rs.getString("name") + "|" + rs.getString("member_id") + "|" + rs.getString("category") + "|" + rs.getString("total_loan")
									+ "*";
					}
					while (rs.next()) {
						result += rs.getString("name") + "|" + rs.getString("member_id") + "|" + rs.getString("category") + "|" + rs.getString("total_loan")
									+ "*";
					}
				}
				else {	//이름 입력 안한 경우, 부류 별 전체 출력
					sql = "select * from db_library.member where category = ? order by total_loan desc";
					pstmt = this.conn.prepareStatement(sql);
					pstmt.setString(1, type);
					
					rs = pstmt.executeQuery();
					
					if (rs.next()) {
						result = rs.getString("name") + "|" + rs.getString("member_id") + "|" + rs.getString("category") + "|" + rs.getString("total_loan")
									+ "*";
					}
					while (rs.next()) {
						result += rs.getString("name") + "|" + rs.getString("member_id") + "|" + rs.getString("category") + "|" + rs.getString("total_loan")
									+ "*";
					}
				}
				
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		dbClose();
		return result;
	}
	
	public String select_book(int book_id) {
		String result = null;
		if (dbConnect()) {
			try {
				String sql = "";
				ResultSet rs = null;
	
				sql = "select * from db_library.book where book_id = ?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setInt(1, book_id);
				
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					result = rs.getString("title") + "|" + rs.getString("isbn") + "|" + rs.getString("author") +"|"
							+ rs.getString("publisher") + "|" + rs.getString("book_id") + "|" + rs.getString("isavailable")
							+ "*";
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		dbClose();
		return result;
	}
	
	public String select_currentUser() {
		String result = null;
		if(dbConnect()) {
			try {
				String sql="";
				ResultSet rs = null;
				//책제목 , isbn, 저자, 출판사, 책번호, 예약/대여
				sql = "select * from db_library.current";
				pstmt = this.conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = rs.getString("member_id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		dbClose();
		return result;
	}
	
	public boolean select_isReserve(String member_id, int book_id) {
		boolean result = false;
		if(dbConnect()) {
			try {
				String sql="";
				ResultSet rs = null;
				//책제목 , isbn, 저자, 출판사, 책번호, 예약/대여
				sql = "select book_id from db_library.reservation where member_id = ? and book_id = ?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setString(1, member_id);
				pstmt.setInt(2, book_id);
				
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		dbClose();
		return result;
	}
	
	public boolean select_isLoan(String member_id, int book_id) {
		boolean result = false;
		if(dbConnect()) {
			try {
				String sql="";
				ResultSet rs = null;
				//책제목 , isbn, 저자, 출판사, 책번호, 예약/대여
				sql = "select book_id from db_library.loan where member_id = ? and book_id = ?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setString(1, member_id);
				pstmt.setInt(2, book_id);
				
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		dbClose();
		return result;
	}
	
	//delete
	public String delete_book(int book_id) {
		String str = null;
		if (dbConnect()) {
			try {
				ResultSet rs = null;
				String sql = "select book_id from db_library.book where book_id = ?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setInt(1, book_id);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					book_id = rs.getInt("book_id");

					sql = "delete from db_library.book where book_id = ?";
					pstmt = this.conn.prepareStatement(sql);
					pstmt.setInt(1, book_id);
					pstmt.executeUpdate();
					str = "success";
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
		dbClose();
		return str;
	}
	
	
	//insert
	public String insert_book(String title, String isbn, String author, String publisher) {
		String ret ="";
		if (dbConnect()) {
			try {
				ResultSet rs = null;
				int id = 0;
				String sql = "select max(book_id) from db_library.book order by book_id desc";
				pstmt = this.conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					id = rs.getInt("max(book_id)") + 1;

					sql = "insert into db_library.book values(?, ?, ?, ?, ?, ?)";
					pstmt = this.conn.prepareStatement(sql);
					
					pstmt.setString(1, title);
					pstmt.setString(2, isbn);
					pstmt.setString(3, author);
					pstmt.setString(4, publisher);
					pstmt.setString(5, "t");
					pstmt.setInt(6, id);
					pstmt.executeUpdate();
				}
				ret = "도서 등록이 완료되었습니다.";
			} catch (SQLException e) {
				e.printStackTrace();
				ret = "도서 등록을 실패하였습니다.";
			}
		}
		
		dbClose();
		return ret;
	}
	
	
	//update
	public boolean update_book(int book_id,String title, String isbn, String author, String publisher) {
		if (dbConnect()) {
			try {
				String sql = "update db_library.book set title = ?, isbn = ?, author = ?, publisher = ? where book_id = ?";
				pstmt = this.conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, isbn);
				pstmt.setString(3, author);
				pstmt.setString(4, publisher);
				pstmt.setInt(5, book_id);
				pstmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		dbClose();
		return false;
	}
}