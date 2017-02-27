package controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import model.Questionaire;
import service.GameHandler;

 
@Controller
@ResponseBody
public class GameController {
 	 
	
	 @PostMapping("/save")
	 public ModelAndView save(@ModelAttribute Questionaire q) {
		 // responses come back in a ',' delimited string so parse then process
		 String[] responseList = q.getAnswer().split(",", -1);		 
		 for(int i=0; i < responseList.length; i ++)
			 System.out.println("in save: " + responseList[i]);
	  
		 GameHandler gameHandler = new GameHandler();
		 gameHandler.save(responseList);
		 
		 return new ModelAndView("done");
	 }
	 
	 
}
