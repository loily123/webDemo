package entity;

public class Admin {
	private String userName, password;
	private int id;

	@Override
	public String toString() {
		return "Admin [userName=" + userName + ", password=" + password + ", id=" + id + "]";
	}

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Admin(String userName, String password, int id) {
		super();
		this.userName = userName;
		this.password = password;
		this.id = id;
	}
}
