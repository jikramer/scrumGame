package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import model.User;
import model.UserDetails;
import utils.DBUtils;

public class UserDetailsDao {

	public UserDetails getUserDetails(User user){
		Connection conn = DBUtils.getConnection();
		UserDetails userDetails = new UserDetails();
				
		try{
	      String query = "SELECT d.*, q.level, q.score  FROM user_details d, user_questionaire_detail q  where d.id = q.id and d.id = (select id from user where userName = '" +  user.getUserName()  +  "')";
 
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        userDetails.setId(rs.getInt("id"));
	        userDetails.setFirstName(rs.getString("first_name"));
	        userDetails.setLastName(rs.getString("last_name"));
	        userDetails.setUserType(rs.getString("user_type"));
	        userDetails.setEmail(rs.getString("email"));
	        userDetails.setQuestionaireLevel(rs.getInt("level"));
	        userDetails.setScore(rs.getInt("score"));
	        
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return userDetails;
	} 

	
	/**
	 * stub for testing db connection, direct queries
	 */
	public static void main(String args[]){
		UserDetailsDao userDetailsDao = new UserDetailsDao();
		User user = new User();
		user.setUserName("aquaman");
		UserDetails userDetails = userDetailsDao.getUserDetails(user );
	}
}
