package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;

import model.Questionaire;
import utils.DBUtils;

public class GameDao {

	public void saveScore(String userName, int score){
		  
		try{
			
			Connection conn = DBUtils.getConnection();
			String sql = "{call spUpdateScore(?,?) }";
			CallableStatement cs = conn.prepareCall(sql);

			cs.setString(1, userName);
			cs.setInt(2, score );
			
			cs.execute();
			
		}
			catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		
	} 
	public static void main(String args[]){
		
		GameDao gameDao = new GameDao();
		gameDao.saveScore("xxsuperaquaman", 20);
		
	}	 
}
