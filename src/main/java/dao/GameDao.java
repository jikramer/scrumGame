package dao;

import java.util.ArrayList;

import model.Questionaire;
//change
public class GameDao {

	public void saveAnswers(Questionaire loadedQuestionaire){
		
		System.out.println(loadedQuestionaire.getAnswer1());
		 
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
