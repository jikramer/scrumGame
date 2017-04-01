package controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import dao.UserDetailsDao;
import model.Questionaire;
import model.User;
import model.UserDetails;
import service.GameHandler;

 
@Controller
@ResponseBody
public class GameController {

    @Autowired
    private HttpSession httpSession; 
    private Questionaire originalQuestionaire; 
    private Questionaire answeredQuestionaire = new Questionaire();
    
 	 @GetMapping("/show")
	    public ModelAndView greetingForm(Model model) {
	      return new ModelAndView("game", "questionaire", new Questionaire());
	 }	 
	 
 	//show 1 question at a time
  	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView save( @ModelAttribute(value="Questionaire") Questionaire questionaire ) {        
		
		originalQuestionaire  = (Questionaire) httpSession.getAttribute("questionaire");
		int currentQuestionNum = originalQuestionaire.getCurrentQuestion();
		if (currentQuestionNum <= 4){
			
			if ( currentQuestionNum ==1)
				answeredQuestionaire.setAnswer1(questionaire.getAnswer1());
			else if ( currentQuestionNum ==2)
				answeredQuestionaire.setAnswer2(questionaire.getAnswer2());
			else if ( currentQuestionNum ==3)
				answeredQuestionaire.setAnswer3(questionaire.getAnswer3());
			else if ( currentQuestionNum ==4)
				answeredQuestionaire.setAnswer4(questionaire.getAnswer4());
  
			originalQuestionaire.setCurrentQuestion(currentQuestionNum + 1);
			httpSession.setAttribute("questionaire", originalQuestionaire);
			 
			return new ModelAndView("game", "Questionaire", originalQuestionaire );
 		}
		ModelAndView results = scoreAndRedirect();
		return results;
	}

	private ModelAndView scoreAndRedirect(){
	
		String userName = httpSession.getAttribute("userName").toString();
 	 	
		GameHandler gameHandler = new GameHandler();
	 	int score = gameHandler.scoreGame(userName, answeredQuestionaire);
	 	
	 	gameHandler.save(userName, score);
	
	 	User user = new User();
		user.setUserName(userName);
	 	UserDetailsDao userDetailsDao = new UserDetailsDao();
		UserDetails userDetails = userDetailsDao.getUserDetails(user);
		if(score >= 80)
			userDetails.setQuestionaireLevel(userDetails.getQuestionaireLevel() + 1);
	 	user.setUserDetails(userDetails);
		
		return new ModelAndView("score","User", user );
		
	}
	

}
