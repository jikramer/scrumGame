package service;

import dao.GameDao;
import model.Questionaire;

public class GameHandler {



	public int scoreGame( String userName, Questionaire loadedQuestionaire ){
		
		int score = doSimpleScoring(loadedQuestionaire);
		return score;
	}	
	
	
	/**
	 * super simple scoring algorithm:  question 1 = answer 5, question 2 = answer 1, question 3 = answer 2, question 4 = answer 3, question 5 = answer 4 
	 * 
	 * @param loadedQuestionaire
	 * @return
	 */
	int doSimpleScoring(Questionaire loadedQuestionaire ) {
		int score = 20;
		
		if ( loadedQuestionaire.getAnswer1().equals("4"))
			score += 20;

		if ( loadedQuestionaire.getAnswer2().equals("1"))
			score += 20;

		if ( loadedQuestionaire.getAnswer3().equals("2"))
			score += 20;	
		
		if ( loadedQuestionaire.getAnswer4().equals("3"))
			score += 20;
	 	 
	
		return score;
		
	}
		
	/**
	 * note - proc updates to next level if score > 80
	 * 
	 * @param userName
	 * @param score
	 */
	public void save ( String userName, int score ){
		
		GameDao gameDAO = new GameDao();
		 
		gameDAO.saveScore(userName, score);
	
	}
}
