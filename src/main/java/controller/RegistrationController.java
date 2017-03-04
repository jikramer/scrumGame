package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.User;
 
@Controller
@ResponseBody
public class RegistrationController {
 	  

	 @GetMapping("/showreg")
	    public ModelAndView greetingForm(Model model) {
	      return new ModelAndView("registration");
	 }

	
	 @PostMapping("/register")
	 public ModelAndView register(User login) {
	     System.out.println("in new user registration"); 


	     System.out.println("userName: " + login.getUserName());
	     System.out.println("password: " + login.getPassword());
	 
	     //create & call registerService
	     //create & call registerDAO
	     
		 return new ModelAndView("dashboard", "user", login );
	
	 }
	 
}
