package service;
 
import dao.LoginDao;
import model.User;

public class LoginHandler {


	public boolean authenticateUser ( String userName, String password){
		LoginDao loginDAO = new LoginDao();
		User user = loginDAO.authenticateUser(userName, password);
		
	    boolean isValid = validatePassword(password, user.getPassword());
		return isValid;
	}
	
	private boolean validatePassword(String originalPassword, String dbPassword){
		
		if(originalPassword.equalsIgnoreCase(dbPassword))
			return true;
		return false;
	}
	
}
