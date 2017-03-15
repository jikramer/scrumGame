package dao;

import java.sql.CallableStatement;
import java.sql.Connection;

import model.User;
import model.UserDetails;
import utils.DBUtils;

public class RegistrationDao {

	public void createUserWithDetails(User user){
		try{
						
			Connection conn = DBUtils.getConnection();
			String sql = "{call spCreateUser(?,?,?,?,?,?) }";
			CallableStatement cs = conn.prepareCall(sql);

			cs.setString(1, user.getUserName());
			cs.setString(2, user.getPassword());
			cs.setString(3, user.getUserDetails().getFirstName());
			cs.setString(4, user.getUserDetails().getLastName());
			cs.setString(5, user.getUserDetails().getUserType());
			cs.setString(6, user.getUserDetails().getEmail());
			
			cs.execute();
			
		}
			catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		
	} 

	/**
	 * stub for testing db connection, direct queries
	 */
	public static void main(String args[]){
		User user = new User();
		user.setUserName("xxsuperaquaman");
		user.setPassword("password");
		
		UserDetails userDetails = new UserDetails();
		userDetails.setFirstName("aqua");
		userDetails.setLastName("man");
		userDetails.setEmail("aquaman@test.com");
		userDetails.setUserType("student");
		
		user.setUserDetails(userDetails); 
		
		
		RegistrationDao registrationDao = new RegistrationDao();
		registrationDao.createUserWithDetails(user);
		
	}
}
