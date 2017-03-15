package service;
 
import dao.LoginDao;
import model.User;

public class LoginHandler {


	public boolean authenticateUser ( User incomingUser){
		LoginDao loginDAO = new LoginDao();
		User user = loginDAO.authenticateUser(incomingUser.getUserName(), incomingUser.getPassword());
		
	    boolean isValid = validatePassword(incomingUser.getPassword(), user.getPassword());
		return isValid;
	}
	
	private boolean validatePassword(String originalPassword, String dbPassword){
		
		if(originalPassword.equalsIgnoreCase(dbPassword))
			return true;
		return false;
	}
	
}
