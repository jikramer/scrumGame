package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.User;
 
@Controller
@ResponseBody
public class LoginController {
 	
	 @GetMapping("/")
	    public ModelAndView greetingForm(Model model) {
	      return new ModelAndView("login");
 	 }

	 @PostMapping("/login")
	 public ModelAndView login(@ModelAttribute User login ) {
	     System.out.println("userName: " + login.getUserName());
	     System.out.println("password: " + login.getPassword());
	 
		 return new ModelAndView("dashboard", "user", login );
	 }
 
	 @PostMapping("/registration")
	 public ModelAndView login() {
	     System.out.println("in new user registration"); 
		 return new ModelAndView("registration");
	 }
	 
}
