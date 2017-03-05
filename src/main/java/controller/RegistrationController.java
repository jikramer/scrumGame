package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.User;
import service.RegistrationHandler;
 
@Controller
@ResponseBody
public class RegistrationController {
 	  

	 @GetMapping("/showreg")
	    public ModelAndView greetingForm(Model model) {
	      return new ModelAndView("registration", "User", new User());
	 }

	
	 @PostMapping("/register")
	 public ModelAndView register(User login) {
		 
		 RegistrationHandler handler = new RegistrationHandler();
		 handler.createUser(login);
		 return new ModelAndView("dashboard", "user", login );
	 }
	 
}
