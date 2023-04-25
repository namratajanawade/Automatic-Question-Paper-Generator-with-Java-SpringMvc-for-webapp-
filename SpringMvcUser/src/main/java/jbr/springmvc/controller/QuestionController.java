package jbr.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jbr.springmvc.model.Question;
import jbr.springmvc.model.User;
import jbr.springmvc.service.QuestionService;

@Controller
public class QuestionController {
  @Autowired
  public QuestionService questionService;

  @RequestMapping(value = "/addquestion", method = RequestMethod.GET)
  public ModelAndView showQuestion(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("addquestion");
    mav.addObject("question", new Question());

    return mav;
  }
  
  @RequestMapping(value = "/addQuestionProcess", method = RequestMethod.POST)
  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
  @ModelAttribute("question") Question question) {

  questionService.addQuestion(question);
  ModelAndView mav = new ModelAndView("addquestionsuccess");
  mav.addObject("topic", question.getTopic());
  mav.addObject("question", question.getQuestion());
  
  return mav;
  }
  
  @RequestMapping(value = "/questionlist")
  public ModelAndView displayQuestions(HttpServletRequest request, Model model) {
	  List<Question> questionsList = questionService.getAllQuestions();
      ModelAndView mav = new ModelAndView("questionlist");
      mav.addObject("questions", questionsList);
      return mav;
  }
  
  @RequestMapping(value = "/questionmodify")
  public ModelAndView displayQuestionsToModify(HttpServletRequest request, Model model) {
	  List<Question> questionsList = questionService.getAllQuestions();
      ModelAndView mav = new ModelAndView("questionmodify");
      mav.addObject("questions", questionsList);
      return mav;
  }
  
  @RequestMapping(value = "/questiondelete")
  public ModelAndView displayQuestionsToDelete(HttpServletRequest request, Model model) {
	  List<Question> questionsList = questionService.getAllQuestions();
      ModelAndView mav = new ModelAndView("questiondelete");
      mav.addObject("questions", questionsList);
      return mav;
  }
  
  @RequestMapping("/editQuestion")
  public ModelAndView editQuestion(@RequestParam("question") String question) {
      Question ques = questionService.getQuestionByQuestion(question);
      ModelAndView mav = new ModelAndView("editQuestion");
      mav.addObject("question", ques);
      return mav;
  }

  @RequestMapping(value="/saveQuestion", method=RequestMethod.POST)
  public ModelAndView saveQuestion(@ModelAttribute("question") Question question) {
      questionService.saveQuestion(question);
      ModelAndView mav = new ModelAndView("redirect:/questionmodify");
      return mav;
  }
  
  @RequestMapping(value="/deleteQuestion")
  public ModelAndView deleteQuestion(@RequestParam("question") String question) {
      questionService.deleteQuestion(question);
      ModelAndView mav = new ModelAndView("redirect:/questiondelete");
      return mav;
  }
  

}