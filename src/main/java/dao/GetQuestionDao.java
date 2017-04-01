package dao;
import java.sql.*;
import model.User;
import model.UserDetails;
import utils.DBUtils;

public class GetQuestionDao {
	
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
	
	public void getMCQ(User user){
		
 	
		Connection conn = DBUtils.getConnection();
 		int incomingLevel = user.getUserDetails().getQuestionaireLevel();

 		String level = convertLevel(incomingLevel);
		 	try{
 				CallableStatement cs = conn.prepareCall("{Call getQuestion(?)}");
				cs.setString(1,level);
				ResultSet rs = cs.executeQuery();
				while(rs.next()){
					rs.getString("question");
					rs.getString("choice_1"); 
					rs.getString("choice_2");
					rs.getString("choice_3");
					rs.getString("choice_4");
				}
				rs.close();
				cs.close();
				}
				catch(Exception e)
				 {
				      System.err.println("Got an exception! ");
				      System.err.println(e.getMessage());
				 }
	}
			
	
		
	public static void main(String args[]){
		GetQuestionDao q = new GetQuestionDao();
		User user = new User();
		user.setId(1);
		UserDetails d = new UserDetails();
		d.setQuestionaireLevel(1);
		user.setUserDetails(d);
		user.setUserName("aquaman");
		q.getMCQ(user);
	
				
	}
		
}
