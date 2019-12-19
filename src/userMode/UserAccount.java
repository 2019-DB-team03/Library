package userMode;

import java.sql.ResultSet;
import java.sql.SQLException;

import dbManage.DBManage;

public class UserAccount { // YAM
	static DBManage db = new DBManage();

	// 로그인을 db에서 수행한 후 결과를 받아오고 html코드 만드는 메소드
	public static String getLoginState(String id, String pw) {
		String result = "";
		String dbResult = db.checkIdPw(id, pw);
		if (dbResult.equals("Connection Error")) {
			result = "Connection Error";
		} else if (dbResult.equals("under") || dbResult.equals("post") || dbResult.equals("faculty")) {
			result += "<p>로그인에 성공했습니다.</p>";
			result += "<input type=\"button\" onclick = \"location.href='userMain.jsp'\" value=\"확인\">";
			// 로그인 성공, 진행하기 버튼
		} else if (dbResult.equals("manager")) {
			result += "<p>관리자로 로그인에 성공했습니다.</p>";
			result += "<input type=\"button\" onclick = \"location.href='managerMain.jsp'\" value=\"확인\">";
			// 관리자 로그인 성공, 진행하기 버튼
		} else {
			result += "<p>로그인에 실패했습니다. 아이디와 비밀번호를 확인해 주세요.</p>";
			result += "<input type=\"button\" onclick = \"window.history.back()\" value=\"확인\">";
			// 로그인 실패, 뒤로가기 버튼
		}
		return result;
	}

	// 회원가입을 db에서 수행한 후 결과를 받아오고 html코드 만드는 메소드
	public static String getJoinState(String name, String id, String pw, String email, String phone, String category) {
		String result = "";
		String dbResult = db.checkJoinVal(name, id, pw, email, phone, category);
		if (dbResult.equals("Connection Error")) {
			result = "Connection Error";
		} else if (dbResult.equals("ok")) {
			result += "<p>회원가입에 성공했습니다. 해당 아이디[" + id + "]로 로그인하실 수 있습니다.</p>";
			result += "<input type=\"button\" onclick = \"location.href='main.jsp'\" value=\"확인\">";
			// 회원가입 성공, 로그인으로 진행하기 버튼
		} else {
			result += "<p>입력하신 id [" + id + "]는 이미 존재하는 아이디입니다.\n다른 아이디를 입력해 주세요</p>";
			result += "<input type=\"button\" onclick = \"window.history.back()\" value=\"확인\">";
			// 회원가입 실패, 뒤로가기 버튼
		}
		return result;
	}

	// 비밀번호를 체크하는 메소드
	public static String getCurrentUserData(String pw) {
		String result = "";
		if (db.checkPw(pw).equals("yes_pw")) {
			result = getInfo(db.currentUserId());
		} else {
			result += "<p>비밀번호가 일치하지 않습니다.\n다시 입력해 주세요</p>";
			result += "<input type=\"button\" onclick = \"window.history.back()\" value=\"확인\">";
		}
		return result;
	}

	// 회원 정보를 출력하는 메소드
	public static String getInfo(String id) {
		String result = "";
		String dbResult = db.userData(id);
		String[] dbResultArr = dbResult.split("\\|");
		result += "<label for=\"id\">id</label><input type=\"text\" id=\"id\" name=\"id\" value=\"" + dbResultArr[0]
				+ "\" readonly /></br>";
		result += "<label for=\"pw\">pw</label><input type=\"password\" id=\"pw\" name=\"pw\" value=\"" + dbResultArr[1]
				+ "\" /></br>";
		result += "<label for=\"name\">name</label><input type=\"text\" id=\"name\" name=\"name\" value=\""
				+ dbResultArr[2] + "\" /></br>";
		result += "<label for=\"email\">email</label><input type=\"email\" id=\"email\" name=\"email\" value=\""
				+ dbResultArr[3] + "\" /></br>";
		result += "<label for=\"phone\">phone</label><input type=\"text\" id=\"phone\" name=\"phone\" value=\""
				+ dbResultArr[4] + "\" /></br>";
		result += "<label for=\"category\">category</label><input type=\"text\" id=\"category\" name=\"category\" value=\""
				+ dbResultArr[5] + "\" readonly /></br>";
		result += "<label for=\"loan\">total_loan</label><input type=\"number\" id=\"loan\" name=\"loan\" value=\""
				+ dbResultArr[6] + "\" readonly /></br>";

		result += "<input type=\"button\" id=\"leave\" value=\"탈퇴\">";
		result += "<input type=\"submit\" id=\"update\" value=\"정보수정\">";
		return result;
	}

	// current를 탈퇴하는 메소드
	public static String leaveCurrentUser() {
		String result = "";
		String dbResult = db.deleteMember(db.currentUserId());
		if (dbResult.equals("ok")) {
			result += "<p>탈퇴에 성공했습니다. 현재 계정은 앞으로 이용하실 수 없습니다.</p>";
			result += "<input type=\"button\" onclick = \"location.href='main.jsp'\" value=\"확인\">";
		} else {
			result += "<p>탈퇴실패</p>";
			result += "<input type=\"button\" onclick = \"window.history.back()\" value=\"확인\">";
		}
		return result;
	}

	// 특정 member를 탈퇴시키는 메소드
	public static String leaveUser(String id) {
		String result = "";
		String dbResult = db.deleteMember(id);
		if (dbResult.equals("ok")) {
			result += "<p>id [" + id + "]가 정상적으로 탈퇴되었습니다.</p>";
			result += "<input type=\"button\" onclick = \"window.history.back()\" value=\"확인\">";
		} else {
			result += "<p>탈퇴실패</p>";
			result += "<input type=\"button\" onclick = \"window.history.back()\" value=\"확인\">";
		}
		return result;
	}

	// current user의 정보를 업데이트하는 메소드
	public static String updateUser(String id, String pw, String name, String email, String phone, String category) {
		String result = "";
		
		String a = db.updateMember(id, "password", pw);
		String b = db.updateMember(id, "name", name);
		String c = db.updateMember(id, "email", email);
		String d = db.updateMember(id, "phone", phone);
		String e = db.updateMember(id, "category", category);

		if (a.equals("ok") && b.equals("ok") && c.equals("ok") && d.equals("ok") && e.equals("ok")) {
			result += "<p>업데이트에 성공했습니다.";
			result += "<input type=\"button\" onclick = \"window.history.back()\" value=\"확인\">";
		} else {
			result += "<p>업데이트실패</p>";
			result += "<input type=\"button\" onclick = \"window.history.back()\" value=\"확인\">";
		}
		return result;
	}
}
