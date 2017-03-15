package service;

import dao.QuestionaireDao;
import model.User;

public class QuestionaireHandler {


	public String getQuestionaireLevelCompleted ( User user){
	
		QuestionaireDao questionaireDao = new QuestionaireDao();
		int level = questionaireDao.getQuestionaireLevel(user);
		String prettyLevel = getPrettyQuestionaireLevel(level);
		return prettyLevel;
	}
	
	private String getPrettyQuestionaireLevel(int level){
		
		switch(level){
			case 1:
				return "Beginner";
			case 2:
				return "Intermediate";
			case 3:
				return "Expert";
				
			default:
				return "newB";
		}
		
	}
}
