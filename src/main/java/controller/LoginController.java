package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.User;
import service.LoginHandler;
 
@Controller
@ResponseBody	
public class LoginController {
 	
	 @GetMapping("/")
	    public ModelAndView greetingForm(Model model) {
	      return new ModelAndView("login", "user", new User());
 	 }

	 @PostMapping("/login")
	 public ModelAndView login(@ModelAttribute User login ) {
		// TODO: check username & pw are valid.. 
	    LoginHandler loginHandler = new LoginHandler();	     
	    boolean isValid = loginHandler.authenticateUser(login.getUserName(), login.getPassword());
	     
	    if(!isValid){
	    	login.setHasErrors(true);
	    	
	    	return new ModelAndView("login", "user", login );
	    }
	    else	
	    	return new ModelAndView("dashboard", "user", login );
	 
	 }
 
	 @PostMapping("/registration")
	 public ModelAndView login() {
	     System.out.println("in new user registration"); 
		 return new ModelAndView("registration");
	 }
	 
}
