package controller;
 

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import dao.QuestionaireDao;
import model.FacultyStudent;
import model.Questionaire;
import model.User;
import service.QuestionaireHandler;
import service.UserDetailsHandler;

@SessionAttributes( { "question_index", "something" })  



@RestController
public class DashboardController {
  @Autowired
  private HttpSession httpSession;

	
  @PostMapping("/initplay" )
  public ModelAndView get() { 
	  //Questionaire loadedQuestionaire = doMockQuestionaireLoad();
	  
	  Questionaire loadedQuestionaire = doQuestionaireLoad();
	  httpSession.setAttribute("questionaire", loadedQuestionaire);
	  return new ModelAndView("game", "Questionaire", loadedQuestionaire );
  }
  
  @PostMapping("/done" )
  public ModelAndView done() { 
	  return new ModelAndView("done", "done", new User());
  }
  

  @PostMapping("/assign" )
  public ModelAndView assign(FacultyStudent facultyStudent) { 
	   
	  UserDetailsHandler userDetailsHandler = new UserDetailsHandler();
	  userDetailsHandler.assignFacultyStudent(facultyStudent);
	  
	  User login = (User) httpSession.getAttribute("login");
	  UserDetailsHandler handler = new UserDetailsHandler();
	  List<User> users = handler.getFacultyStudentDetails(login);
  	  
	  ModelAndView mv = new ModelAndView("facultyDashboard", "users", users);
	  mv.getModelMap().addAttribute("FacultyStudent", new FacultyStudent());

   	  ArrayList<User> historicalUsers = (ArrayList<User>) userDetailsHandler.getFacultyStudentHistory(login);
	  mv.getModelMap().addAttribute("historicalUsers", historicalUsers);

	  
	  return mv;
   }

  
  private Questionaire doQuestionaireLoad(){
	  User user = (User) httpSession.getAttribute("login");
	  QuestionaireHandler handler = new QuestionaireHandler();
	  Questionaire q1 = handler.getQuestionaire(user);
	   
	  return q1;
  }

  
  
  private Questionaire doMockQuestionaireLoad(){
	  
	//TODO: initialize properly
	   
	  Questionaire q1 = new Questionaire();
	  
	  
	  q1.setQuestion1("What's a standup meeting?");
	  q1.setQuestion2("How long is an agile sprint?");
	  q1.setQuestion3("What is XP?");
	  q1.setQuestion4("What's a work backlog?");
	  q1.setQuestion5("Is there such a thing as a scrum master?");
	  
	  q1.setAnswer1("answer 1");
	  q1.setAnswer1("answer 1");
	  q1.setAnswer2("answer 2");
	  q1.setAnswer3("answer 3");
	  q1.setAnswer4("answer 4");
	  q1.setAnswer5("answer 5");
	  
	  return q1;
  }
}
