package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.FacultyStudent;
import model.User;
import model.UserDetails;
import utils.DBUtils;

public class UserDetailsDao {

	public UserDetails getUserDetails(User user){
		Connection conn = DBUtils.getConnection();
		UserDetails userDetails = new UserDetails();
				
		try{
	      String query = "SELECT d.*, q.level, q.score  FROM user_details d, user_questionaire_detail q  "
	      		+ "where d.id = q.id "
	      		+ "and q.last_update_dt = (select max(last_update_dt) from user_questionaire_detail where id = (select id from user where userName = '" +  user.getUserName()  +  "')"
	        	+ "and d.id = (select id from user where userName = '" +  user.getUserName()  +  "'))";
 
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


	public List<User> getFacultyStudentDetails(User user){
		
		List<User> users = new ArrayList<User>();
		
		try{
			
			Connection conn = DBUtils.getConnection();
			String sql = "{call spGetFacultyStudent(?) }";
			CallableStatement cs = conn.prepareCall(sql);

			cs.setString(1, user.getUserName());
			ResultSet rs = cs.executeQuery();
	       
			while (rs.next())
		      {
		        User u = new User();
		        UserDetails d = new UserDetails();
		        
		        u.setUserName(rs.getString("username"));
				d.setQuestionaireLevel(rs.getInt("level"));
				d.setScore(rs.getInt("score"));
				
				u.setUserDetails(d);
				users.add(u);
		      }
	 	    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
			return users;
	} 

	

	public List<User> getFacultyStudentHistory(User user){
		
		List<User> users = new ArrayList<User>();
		
		try{
			
			Connection conn = DBUtils.getConnection();
			String sql = "{call spGetFacultyStudentHistory(?) }";
			CallableStatement cs = conn.prepareCall(sql);

			cs.setString(1, user.getUserName());
			ResultSet rs = cs.executeQuery();
	       
			while (rs.next())
		      {
		        User u = new User();
		        UserDetails d = new UserDetails();
		        
		        u.setUserName(rs.getString("username"));
				d.setQuestionaireLevel(rs.getInt("level"));
				d.setScore(rs.getInt("score"));
				d.setTimestamp(rs.getString("last_update_dt"));
				
				u.setUserDetails(d);
				users.add(u);
		      }
	 	    }
		    catch (Exception e)
		    {
		      System.err.println("Got an exception! ");
		      System.err.println(e.getMessage());
		    }
			return users;
	} 

	
	
	
	public void assignFacultyStudent(FacultyStudent facultyStudent){
		try{
			
			Connection conn = DBUtils.getConnection();
			String sql = "{call spAssignFacultyStudent(?,?) }";
			CallableStatement cs = conn.prepareCall(sql);

			cs.setString(1, facultyStudent.getFaculty());
			cs.setString(2, facultyStudent.getStudent());
			
			cs.executeQuery();

	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
 
}
	


	public List<User> getStudentHistory(User user){
		
 		List<User> users = new ArrayList<User>();
		
		try{
			
			Connection conn = DBUtils.getConnection();
			String sql = "{call spGetStudentHistory(?) }";
			CallableStatement cs = conn.prepareCall(sql);

			cs.setString(1, user.getUserName()); 
			ResultSet rs = cs.executeQuery();
		
			while (rs.next())
		      {
		        User u = new User();
		        UserDetails d = new UserDetails();
		        
		        u.setUserName(rs.getString("username"));
				d.setQuestionaireLevel(rs.getInt("level"));
				d.setScore(rs.getInt("score"));
				d.setTimestamp(rs.getString("last_update_dt"));
				u.setUserDetails(d);
				
				users.add(u);
		      }
  			
			
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return users;
	}

	
	
	/**
	 * stub for testing db connection, direct queries
	 */
	public static void main(String args[]){
		UserDetailsDao userDetailsDao = new UserDetailsDao();
		User user = new User();
		user.setUserName("testxs");
	//	UserDetails userDetails = userDetailsDao.getUserDetails(user );
		List<User> users =  userDetailsDao.getFacultyStudentDetails(user);
		System.out.println(users.get(0).getUserName());
	}
}
