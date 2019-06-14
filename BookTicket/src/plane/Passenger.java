package plane;

public class Passenger {// 乘客的属性

	private String passengerId;//乘客编号
	private String userName;
	private String realName;
	private String sex;
	private String teleNum;//手机号码
	private String identityId;
	private String password;

	// 设置构造器
	public Passenger() {
	}

	public Passenger(String userName, String name, String teleNum, String identityId, String pass, String passengerId) {
		this.userName = userName;
		this.realName = name;
		this.teleNum = teleNum;
		this.identityId = identityId;
		this.password = pass;
		this.passengerId = passengerId;
		int sexNum = Integer.parseInt(identityId.substring(16, 17));
		this.sex = sexNum % 2 == 0?"FEMALE":"MALE";
	}

	// 设置set和get方法
	public String getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(String passengerId) {
		this.passengerId = passengerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSex() {
		return sex;
	}

	public String getTeleNum() {
		return teleNum;
	}

	public void setTeleNum(String teleNum) {
		this.teleNum = teleNum;
	}

	public String getIdentityId() {
		return identityId;
	}

	public void setIdentityId(String identityId) {
		this.identityId = identityId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
