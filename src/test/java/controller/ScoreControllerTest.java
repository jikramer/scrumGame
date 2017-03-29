package controller;

import static org.junit.Assert.fail;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import model.Questionaire;

public class ScoreControllerTest {
	@Autowired
    private HttpSession httpSession; 
	
	@Test
	public void testGreetingForm() {
		GameController controller = new GameController();
		httpSession.setAttribute("name", "test");  		
		controller.save(new Questionaire());
		System.out.println("hey");
		
		fail("Not yet implemented");
	}

}
