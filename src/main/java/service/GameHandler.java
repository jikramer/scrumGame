package service;

import dao.GameDao;
import model.Questionaire;

public class GameHandler {


	public void save ( String userName, Questionaire loadedQuestionaire ){
	
		GameDao gameDAO = new GameDao();
		gameDAO.saveAnswers(loadedQuestionaire);
	
	}
}
