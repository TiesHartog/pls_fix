package ties.hartog.pls_fix.domain;

public class User {
	private int userID;
	private String password;
	private String username;
	private String urole;
	
	public User(int uID, String name, String pass, String role) {
		userID = uID;
		password = name;
		username = pass;
		urole = role;
	}
	public User(String name, String pass, String role) {
		password = name;
		username = pass;
		urole = role;
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUrole() {
		return urole;
	}

	public void setUrole(String urole) {
		this.urole = urole;
	}
}
