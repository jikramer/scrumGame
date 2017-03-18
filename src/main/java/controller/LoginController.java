package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.User;
import model.UserDetails;
import service.LoginHandler;
import service.QuestionaireHandler;
import service.UserDetailsHandler;
 
@Controller
@ResponseBody	
public class LoginController {

    @Autowired
    private HttpSession httpSession;
	
	 @GetMapping("/")
	    public ModelAndView greetingForm(Model model) {
	      return new ModelAndView("login", "user", new User());
 	 }

	 @PostMapping("/login")
	 public ModelAndView login(@ModelAttribute User login ) {
		// TODO: check username & pw are valid.. 
	    LoginHandler loginHandler = new LoginHandler();	     
	    boolean isValid = loginHandler.authenticateUser(login);
	    
	    httpSession.setAttribute("userName", login.getUserName());
	    
	    if(!isValid){
	    	login.setHasErrors(true);
	    	return new ModelAndView("login", "user", login );
	    }
	    else{	
	    	UserDetailsHandler userDetailsHandler = new UserDetailsHandler();
	    	UserDetails userDetails = userDetailsHandler.getUserDetails(login);
	    	login.setUserDetails(userDetails);
	    	
	    	QuestionaireHandler questionaireHandler = new QuestionaireHandler();
	    	int levelCompleted = questionaireHandler.getQuestionaireLevelCompleted(login);
	    	userDetails.setQuestionaireLevel(levelCompleted);
	    	
	    	return new ModelAndView("dashboard", "user", login );
	    }	
	 }

	 
	 
	 
	 
	 @PostMapping("/registration")
	 public ModelAndView login() {
	     System.out.println("in new user registration"); 
		 return new ModelAndView("registration");
	 }
}
