package service;
 
import java.util.List;

import dao.UserDetailsDao;
import model.FacultyStudent;
import model.User;
import model.UserDetails;

public class UserDetailsHandler {

 	public UserDetails getUserDetails(User user){
 		UserDetailsDao userDetailsDao = new UserDetailsDao();
		UserDetails userDetails = userDetailsDao.getUserDetails(user);
		return userDetails;
 	}
 	
 	public List<User> getFacultyStudentDetails(User user){
 		UserDetailsDao userDetailsDao = new UserDetailsDao();
 		List<User> users = userDetailsDao.getFacultyStudentDetails(user);
 		return users;
 	}
 	 
 	public void assignFacultyStudent(FacultyStudent facultyStudent){
 		UserDetailsDao userDetailsDao = new UserDetailsDao();
 		userDetailsDao.assignFacultyStudent(facultyStudent);
 	}

 	public List<User> getStudentHistory(User user){
 		UserDetailsDao userDetailsDao = new UserDetailsDao();
 		List<User> users = userDetailsDao.getStudentHistory(user);
 		return users;
 	}

 	public List<User> getFacultyStudentHistory(User user){
 		UserDetailsDao userDetailsDao = new UserDetailsDao();
 		List<User> users = userDetailsDao.getFacultyStudentHistory(user);
 		return users;
 	}
 	
 	
}
