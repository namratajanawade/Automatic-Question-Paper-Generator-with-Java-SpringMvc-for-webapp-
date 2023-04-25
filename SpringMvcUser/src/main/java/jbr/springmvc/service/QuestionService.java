package jbr.springmvc.service;

import java.sql.Timestamp;
import java.util.List;

import jbr.springmvc.model.Question;
import jbr.springmvc.model.ResultHistory;

public interface QuestionService {

  int addQuestion(Question question);
  
  public List<String> getAllTopics();
  
  public List<Question> getQuestionsByTopic(String topic);
  
  public List<Question> getAllQuestions();
  
  public Question getQuestionByQuestion(String question);
  
  public void saveQuestion(Question question);
  
  public void deleteQuestion(String question);
  
  public int saveResult(String username, Timestamp time, int score, String topic);
  
  public List<ResultHistory> getAllResultHistory(String username);
}