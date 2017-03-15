package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import model.User;
import model.UserDetails;
import utils.DBUtils;

public class QuestionaireDao {

	public int getQuestionaireLevel(User user){
		Connection conn = DBUtils.getConnection();
		UserDetails userDetails = new UserDetails();
				
		try{
	      String query = "SELECT questionaire_level FROM user_details where username = '" +  user.getUserName()  + "'";
 
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        userDetails.setQuestionaireLevel(rs.getInt("questionaire_level")); 
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return userDetails.getQuestionaireLevel();
	} 

	
	/**
	 * stub for testing db connection, direct queries
	 */
	public static void main(String args[]){
		QuestionaireDao questionaireDao = new QuestionaireDao();
		User user = new User();
		user.setId(1);
		user.setUserName("aquaman");
		questionaireDao.getQuestionaireLevel(user);
		
	}
}
