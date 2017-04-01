package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Questionaire;
import model.User;
import model.UserDetails;
import utils.DBUtils;

public class QuestionaireDao {

	public int getQuestionaireLevel(User user){
		Connection conn = DBUtils.getConnection();
		UserDetails userDetails = new UserDetails();
				
		try{
	      String query = "SELECT d.level FROM user_questionaire_detail d, user u where d.id = u.id and  u.username = '" +  user.getUserName()  + "'";
 
	      Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      while (rs.next())
	      {
	        userDetails.setQuestionaireLevel(rs.getInt("level")); 
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


	private String convertLevel(int level){
		if (level==1){
			return "Beginner";
		}
		else if (level==2){
			return "Intermediate";
		}
		else if (level==3){
			return "Expert";
		}
		else return "Beginner";
	}
	
	public Questionaire getQuestionaire(User user){
		
		Questionaire q = new Questionaire();
		Connection conn = DBUtils.getConnection();
 		int incomingLevel = user.getUserDetails().getQuestionaireLevel();

 		String level = convertLevel(incomingLevel);
		 	try{
 				CallableStatement cs = conn.prepareCall("{Call getQuestion(?)}");
				cs.setString(1,level);
				ResultSet rs = cs.executeQuery();
				while(rs.next()){
					q.setQuestion1(rs.getString("question_1"));
					q.setQuestion2(rs.getString("question_2"));
					q.setQuestion3(rs.getString("question_3"));
					q.setQuestion4(rs.getString("question_4"));
					q.setAnswer1(rs.getString("choice_1")); 
					q.setAnswer2(rs.getString("choice_2"));
					q.setAnswer3(rs.getString("choice_3"));
					q.setAnswer4(rs.getString("choice_4"));
				}
				rs.close();
				cs.close();
				}
				catch(Exception e)
				 {
				      System.err.println("Got an exception! ");
				      System.err.println(e.getMessage());
				 }
		 	return q;
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
