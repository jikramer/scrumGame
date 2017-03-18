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

import dao.LoginDao;
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
	
 	 @GetMapping("/show")
	    public ModelAndView greetingForm(Model model) {
	      return new ModelAndView("game", "questionaire", new Questionaire());
	 }	 
	 

	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView save( @ModelAttribute(value="Questionaire") Questionaire questionaire ) {        
		String userName = httpSession.getAttribute("userName").toString();
		
		System.out.println("userName from session: " + userName);
		
		GameHandler gameHandler = new GameHandler();
	
		int score = gameHandler.scoreGame(userName, questionaire);
		
		gameHandler.save(userName, score);
		User user = new User();
		user.setUserName(userName);
		
		UserDetailsDao userDetailsDao = new UserDetailsDao();
		UserDetails userDetails = userDetailsDao.getUserDetails(user);
		
		user.setUserDetails(userDetails);
		
		return new ModelAndView("score","User", user );
	
	}
}
