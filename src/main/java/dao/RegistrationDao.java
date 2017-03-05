package dao;

import java.sql.CallableStatement;
import java.sql.Connection;

import model.User;
import utils.DBUtils;

public class RegistrationDao {

	public void createUserWithDetails(User user){
		//TODO - update proc to add rows to userdetails
		//TODO - pull userDetail params out of user ( they are there! ) and add to the callable statement
		try{
						
			Connection conn = DBUtils.getConnection();
			String sql = "{call spCreateUser(?,?) }";
			CallableStatement cs = conn.prepareCall(sql);
	
			cs.setString(1, user.getUserName());
			cs.setString(2, user.getPassword());
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
		user.setUserName("regtest");
		user.setPassword("password");
		
		RegistrationDao registrationDao = new RegistrationDao();
		registrationDao.createUserWithDetails(user);
		
	}
}
