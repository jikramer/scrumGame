package service;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Questionaire;

public class TestSimpleScoring {

	/**
	 * Testing each node with valid result
	 * Note: valid result = all nodes true & score adjusted for each.  Total result should = 100
	 */
	@Test
	public void testSimpleScoringValidNodes() {

		ArrayList<String> answers = buildAnswerSet("4","1","2","3");	
		Questionaire loadedQuestionaire = getLoadedQuestionaire(answers);
		GameHandler gameHandler = new GameHandler();
		int result = gameHandler.doSimpleScoring( loadedQuestionaire);
		
		assertEquals(result, 100);
	}

	
	/**
	 * Testing first node with valid result
	 * Note: valid result = first node true others false.  Total result should = 40
	 */
	
	@Test
	public void testSimpleScoringFrontEdge() {

		ArrayList<String> answers = buildAnswerSet("4","","","");	
		Questionaire loadedQuestionaire = getLoadedQuestionaire(answers);
		GameHandler gameHandler = new GameHandler();
		int result = gameHandler.doSimpleScoring( loadedQuestionaire);
		
		assertEquals(result, 40);
	}

	/**
	 * Testing each node with valid result
	 * Note: valid result = last node true others false.  Total result should = 40
	 */
	
	@Test
	public void testSimpleScoringBackEdge() {

		ArrayList<String> answers = buildAnswerSet("","","","3");	
		Questionaire loadedQuestionaire = getLoadedQuestionaire(answers);
		GameHandler gameHandler = new GameHandler();
		int result = gameHandler.doSimpleScoring( loadedQuestionaire);
		
		assertEquals(result, 40);
	}
	
	
	//helper method to build questionnaire
	private Questionaire getLoadedQuestionaire(ArrayList<String> answers){
		Questionaire questionaire = new Questionaire();
		questionaire.setAnswer1(answers.get(0));
		questionaire.setAnswer2(answers.get(1));
		questionaire.setAnswer3(answers.get(2));
		questionaire.setAnswer4(answers.get(3));
		
		return questionaire;
	}
	
	//helper  method to populate answer set
	private ArrayList<String> buildAnswerSet(String a1, String a2, String a3, String a4){
		ArrayList<String> answers = new ArrayList<String>();
		answers.add(a1);
		answers.add(a2);
		answers.add(a3);
		answers.add(a4);
		
		return answers;
	}

}
