package plane;

import java.util.ArrayList;

public class Admin {

	private String userName;
	private String password;
	private String realName;


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

	public Admin() {
	}
	

	public Admin(String a, String p, String r) {
		userName = a;
		password = p;
		realName = r;
	}

}
