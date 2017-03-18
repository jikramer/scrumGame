package service;

import dao.QuestionaireDao;
import model.User;

public class QuestionaireHandler {


	public int getQuestionaireLevelCompleted ( User user){
	
		QuestionaireDao questionaireDao = new QuestionaireDao();
		int level = questionaireDao.getQuestionaireLevel(user);
		return level;
	}
	
	public String getPrettyQuestionaireLevel(int level){
		
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
