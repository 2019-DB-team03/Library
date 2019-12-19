package userMode;

import dbManage.DBManage;

public class UserAccount {	//YAM
	static DBManage db = new DBManage();

	//로그인을 db에서 수행한 후 결과를 받아오고 html코드 만드는 메소드
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

	//회원가입을 db에서 수행한 후 결과를 받아오고 html코드 만드는 메소드
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
}
