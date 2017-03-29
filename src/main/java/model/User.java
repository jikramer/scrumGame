package model;

public class User {

	private int id;
	private String userName;
	private String password;
	private UserDetails userDetails;
	
	//todo: find a better home for these
	private boolean hasErrors;
	private String loginErrorMessage = "Invalid userId or password, please try again or register";
	
	
	public User(){
		userDetails = new UserDetails();
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
	
	public UserDetails getUserDetails() {
		return userDetails;
	}
	
	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean getHasErrors() {
		return hasErrors;
	}
	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}
	public String getLoginErrorMessage() {
		return loginErrorMessage;
	}
	public void setLoginErrorMessage(String loginErrorMessage) {
		this.loginErrorMessage = loginErrorMessage;
	}

	
}
