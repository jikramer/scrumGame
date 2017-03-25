package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.FacultyStudent;
import model.User;
import model.UserDetails;
import service.RegistrationHandler;
import utils.Constants;
 
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
		 	
		 UserDetails userDetails = login.getUserDetails();
		 
		 if(userDetails.getUserType().equals(String.valueOf(Constants.FACULTY.value()))){
			  //list of new faculty's users will always be empty
			List<User> users = new ArrayList<User>();
			    
			ModelAndView mv = new ModelAndView("facultyDashboard", "users", users );
			mv.getModelMap().addAttribute("FacultyStudent", new FacultyStudent());
			
			return mv;
		 }
	 
		 userDetails.setQuestionaireLevel(Constants.BEGINNER.value());
		 login.setUserDetails(userDetails);
		 return new ModelAndView("dashboard", "user", login );
	 }
	 
}
