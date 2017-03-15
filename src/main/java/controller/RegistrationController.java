package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.User;
import model.UserDetails;
import service.RegistrationHandler;
 
@Controller
@ResponseBody
public class RegistrationController {
 	  
    @Autowired
    private HttpSession httpSession;

	 @GetMapping("/showreg")
	    public ModelAndView greetingForm(Model model) {
	      return new ModelAndView("registration", "User", new User());
	 }

	
	 @PostMapping("/register")
	 public ModelAndView register(User login) {
		 httpSession.setAttribute("userName", login.getUserName());
		 
		 RegistrationHandler handler = new RegistrationHandler();
		 handler.createUser(login);
		 	
		 UserDetails userDetails = new UserDetails();
		 userDetails.setQuestionaireLevel(1);
		 
		 login.setUserDetails(userDetails);
		 return new ModelAndView("dashboard", "user", login );
	 }
	 
}
