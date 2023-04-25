package jbr.springmvc.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jbr.springmvc.model.Question;
import jbr.springmvc.model.Result;
import jbr.springmvc.model.ResultHistory;
import jbr.springmvc.service.QuestionService;

@Controller
public class TestPaperController {
    @Autowired
    public QuestionService questionService;
    
    @RequestMapping(value = "/choosetopic", method = RequestMethod.GET)
    public ModelAndView showTestPaper() {
        ModelAndView mav = new ModelAndView("choosetopic");
        List<String> topics = questionService.getAllTopics();
        mav.addObject("topics", topics);
        return mav;
    }
    
    @RequestMapping(value = "/testpaper", method = RequestMethod.POST)
    public ModelAndView generateTestPaper(@RequestParam("topic") String topic) {
        ModelAndView mav = new ModelAndView("testpaper");
        List<Question> questions = questionService.getQuestionsByTopic(topic);
        mav.addObject("chosentopic", topic);
        mav.addObject("questions", questions);
        return mav;
    }
    
    @PostMapping("/testresult")
    public ModelAndView showTestResult(HttpServletRequest request, HttpSession session, @RequestParam("topic") String topic) {
    	
   	 Enumeration<String> parameterNames = request.getParameterNames();
     List<Result> userAnswers = new ArrayList<Result>();
     int score = 0;
     
     while (parameterNames.hasMoreElements()) {
    	    String paramName = parameterNames.nextElement();
    	    if (paramName.startsWith("answer")) {
    	        String index = paramName.substring(6);
    	        String question = request.getParameter("question" + index);
    	        String userAnswer = request.getParameter(paramName);
    	        String actualAnswer = request.getParameter("actualAnswer" + index);
    	        Result questionAnswer = new Result(question, actualAnswer, userAnswer);
    	        if (questionAnswer.isCorrect()) {
    	            score++;
    	        }
    	        userAnswers.add(questionAnswer);
    	    }
    }
     
     String username = (String) session.getAttribute("username");
     ModelAndView mav = new ModelAndView("testresult");
     mav.addObject("results", userAnswers);
     mav.addObject("score", score);
     
     Timestamp timestamp = new Timestamp(System.currentTimeMillis());
     questionService.saveResult(username, timestamp, score, topic);
     
     return mav;
   }
    
    @RequestMapping(value = "/resultlist")
    public ModelAndView displayResults(HttpServletRequest request, Model model, HttpSession session) {
    	String username = (String) session.getAttribute("username");
  	  	List<ResultHistory> resultList = questionService.getAllResultHistory(username);
        ModelAndView mav = new ModelAndView("resultlist");
        mav.addObject("results", resultList);
        return mav;
    }

}