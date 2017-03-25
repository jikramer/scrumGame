package controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.FacultyStudent;
import model.User;
import model.UserDetails;
import service.LoginHandler;
import service.QuestionaireHandler;
import service.UserDetailsHandler;
import utils.Constants;
 
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
	    LoginHandler loginHandler = new LoginHandler();	     
	    boolean isValid = loginHandler.authenticateUser(login);
	    httpSession.setAttribute("userName", login.getUserName());
	    
	    if(!isValid){
	    	login.setHasErrors(true);
	    	return new ModelAndView("login", "user", login );
	    }
	    
	    ModelAndView processedLoginModelAndView = processLogin(login);
	    return processedLoginModelAndView;
	 }

	 /**
	  * helper method to handle pulling required details of login if user is a faculty or student user 
	  *  
	  * @param login
	  * @return ModelAndView for display
	  */
	 private ModelAndView processLogin(User login){
			UserDetailsHandler userDetailsHandler = new UserDetailsHandler();
	    	login.setUserDetails(userDetailsHandler.getUserDetails(login));
	    	
	    	//user is a faculty member, prep & show faculty dashboard
	    	if(login.getUserDetails().getUserType().equals(String.valueOf(Constants.FACULTY.value()))){
	    		ArrayList<User> users = (ArrayList<User>) getStudentFacultyDetails(login);
	    		 
	    		ModelAndView mv = new ModelAndView("facultyDashboard", "users", users);
	    		mv.getModelMap().addAttribute("FacultyStudent", new FacultyStudent());
	    		return mv;
	    	}	
	    	//user is a student, prep & show student dashboard
	    	else{
	    		login = getStudentDashboardDetails(login);
	    		return new ModelAndView("dashboard", "user", login );
	    	}
 	 }
	 
	 
	 private List<User> getStudentFacultyDetails(User login){
		 UserDetailsHandler handler = new UserDetailsHandler();
		 List<User> users = handler.getFacultyStudentDetails(login);
		 return users;
	 }
	 
	 private User getStudentDashboardDetails(User login){
			QuestionaireHandler questionaireHandler = new QuestionaireHandler();
	    	int levelCompleted = questionaireHandler.getQuestionaireLevelCompleted(login);
	    	login.getUserDetails().setQuestionaireLevel(levelCompleted);
	    	return login;
	 }
	 
	 
	 
	 @PostMapping("/registration")
	 public ModelAndView login() {
	     System.out.println("in new user registration"); 
		 return new ModelAndView("registration");
	 }
}
