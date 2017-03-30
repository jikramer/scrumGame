package dao;
import java.sql.*;
import model.User;
import model.UserDetails;
import utils.DBUtils;

public class GetQuestionDao {
public void getMCQ(User user){
		
		Connection conn = DBUtils.getConnection();
		
		int level = user.getUserDetails().getQuestionaireLevel();
		
		switch(level){
		case 1 : 
			try{
				
				CallableStatement cs = conn.prepareCall("{Call getQuestion(?)}");
				cs.setString(1,"Beginner");
				ResultSet rs = cs.executeQuery();
				while(rs.next()){
				System.out.println(rs.getString("question") + "\na)" + rs.getString("choice_1") +"\nb)"+rs.getString("choice_2")+ "\nc)" + rs.getString("choice_3")+"\nd)"+rs.getString("choice_4")+"\n");
				}
				rs.close();
				cs.close();
				}
				catch(Exception e)
				 {
				      System.err.println("Got an exception! ");
				      System.err.println(e.getMessage());
				    }
			break;
	case 2 : 
		try{
			
			CallableStatement cs = conn.prepareCall("{Call getQuestion(?)}");
			cs.setString(1,"Intermediate");
			ResultSet rs = cs.executeQuery();
			while(rs.next()){
			System.out.println(rs.getString("question") + "\na)" + rs.getString("choice_1") +"\nb)"+rs.getString("choice_2")+ "\nc)" + rs.getString("choice_3")+"\nd)"+rs.getString("choice_4")+"\n");
			}
			rs.close();
			cs.close();
			}
			catch(Exception e)
			 {
			      System.err.println("Got an exception! ");
			      System.err.println(e.getMessage());
			    }
		break;
	case 3 : 
		try{
			
			CallableStatement cs = conn.prepareCall("{Call getQuestion(?)}");
			cs.setString(1,"Expert");
			ResultSet rs = cs.executeQuery();
			while(rs.next()){
			System.out.println(rs.getString("question") + "\na)" + rs.getString("choice_1") +"\nb)"+rs.getString("choice_2")+ "\nc)" + rs.getString("choice_3")+"\nd)"+rs.getString("choice_4")+"\n");
			}
			rs.close();
			cs.close();
			}
			catch(Exception e)
			 {
			      System.err.println("Got an exception! ");
			      System.err.println(e.getMessage());
			    }
		break;
	 default:  break;	
		}
	
	
			
	}
		
	public static void main(String args[]){
		GetQuestionDao q = new GetQuestionDao();
		User user = new User();
		user.setId(2);
		UserDetails d = new UserDetails();
		d.setQuestionaireLevel(1);
		user.setUserDetails(d);
		user.setUserName("aquaman");
		q.getMCQ(user);
	
				
	}
		
}
