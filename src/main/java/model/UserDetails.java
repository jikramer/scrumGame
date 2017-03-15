package model;

public class UserDetails {

	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private String userType;
	private int questionaireLevel;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getQuestionaireLevel() {
		return questionaireLevel;
	}
	public void setQuestionaireLevel(int questionaireLevel) {
		this.questionaireLevel = questionaireLevel;
	}
}
