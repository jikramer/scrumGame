package controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

 
@Controller
@ResponseBody
public class ScoreController {
 	
	@RequestMapping(value="/score", method=RequestMethod.POST)
	    public ModelAndView greetingForm(Model model) {
	      return new ModelAndView("done");
	 }	 
}
