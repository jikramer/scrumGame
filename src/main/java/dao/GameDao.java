package dao;

import java.util.ArrayList;
//change
public class GameDao {

	public void saveAnswers(String[] answers){
		
		for(int i=0; i < answers.length; i ++)
			 System.out.println("saving answer to database:  " + answers[i]);
	  
	}

	/**
	 * test comment
	 * @return
	 */
	public ArrayList<String> getAnswers(){
		ArrayList<String> answers = new ArrayList<String>();
	
		System.out.println("returning answers from db");
			
		return answers;
	}
	
}
