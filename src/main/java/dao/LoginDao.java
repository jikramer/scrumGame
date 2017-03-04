package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import model.User;
import utils.DBUtils;

public class LoginDao {

	public User authenticateUser(String userName, String password){
		Connection conn = DBUtils.getConnection();
		User user = new User();
				
		try{
	      String query = "SELECT * FROM user where username = '" +  userName  + "' and password = '" +password + "'";
 
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        user.setId(rs.getInt("id"));
	        user.setUserName(rs.getString("username"));
	        user.setPassword(rs.getString("password"));
	        user.setUserDetailsId(rs.getInt("userdetails_id"));
	         
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return user;
	} 

	
	/**
	 * stub for testing db connection, direct queries
	 */
	public static void main(String args[]){
		LoginDao loginDao = new LoginDao();
		loginDao.authenticateUser("aquaman", "password");
		
	}
}
