package plane;

import java.util.ArrayList;

public class Admin {
	// Admin�࣬��������Ӧ�Ĺ���Ա��һЩ�������Ժͷ�����
	private String userName;
	private String password;
	private String realName;

	// ����set��get����
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	static ArrayList<Admin> admin = new ArrayList<>();

	// ���ù�����
	public Admin() {
	}
	

	public Admin(String a, String p, String r) {
		userName = a;
		password = p;
		realName = r;
	}

}
