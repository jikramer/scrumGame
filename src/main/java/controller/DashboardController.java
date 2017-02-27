package controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import groovyjarjarantlr.collections.List;
import model.Questionaire;
import model.User;

@RestController
public class DashboardController {

  @PostMapping("/initplay")
  public ModelAndView get() { 
	  
	  ArrayList<Questionaire> questionList = doMockQuestionaireLoad();
	  return new ModelAndView("game", "questionaires", questionList );
  }
  
  private ArrayList<Questionaire> doMockQuestionaireLoad(){
	  
	//TODO: initialize properly
	  
	  ArrayList<Questionaire> qList = new ArrayList<Questionaire>();
	  
	  Questionaire q1 = new Questionaire();
	  Questionaire q2 = new Questionaire();
	  Questionaire q3 = new Questionaire();
	  Questionaire q4 = new Questionaire();
	  Questionaire q5 = new Questionaire();

	  q1.setQuestion("What's a standup meeting?");
	  q2.setQuestion("How long is an agile sprint?");
	  q3.setQuestion("What is XP?");
	  q4.setQuestion("What's a work backlog?");
	  q5.setQuestion("Is there such a thing as a scrum master?");
	  
	  qList.add(q1);
	  qList.add(q2);
	  qList.add(q3);
	  qList.add(q4);
	  qList.add(q5);
	  
	  return qList;
  }
}
