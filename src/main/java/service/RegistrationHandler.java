package service;
 
import dao.RegistrationDao;
import model.User;

public class RegistrationHandler {

	public void createUser ( User user){
		RegistrationDao registrationDao = new RegistrationDao();
		registrationDao.createUserWithDetails(user);
	}
	 
	
}
