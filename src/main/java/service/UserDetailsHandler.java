package service;
 
import dao.UserDetailsDao;
import model.User;
import model.UserDetails;

public class UserDetailsHandler {

 	public UserDetails getUserDetails(User user){
		UserDetailsDao userDetailsDao = new UserDetailsDao();
		UserDetails userDetails = userDetailsDao.getUserDetails(user);
		return userDetails;

 	}
}
