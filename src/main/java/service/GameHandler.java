package service;

import dao.GameDao;

public class GameHandler {


	public void save ( String[] listOfAnswers){
	
		GameDao gameDAO = new GameDao();
		gameDAO.saveAnswers(listOfAnswers);
	
	}
}
