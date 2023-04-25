package jbr.springmvc.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jbr.springmvc.dao.QuestionDao;
import jbr.springmvc.model.Question;
import jbr.springmvc.model.ResultHistory;

public class QuestionServiceImpl implements QuestionService {

  @Autowired
  public QuestionDao questionDao;

  public int addQuestion(Question question) {
    return questionDao.addQuestion(question);
  }


public List<String> getAllTopics() {
	return questionDao.getAllTopics();
}

public List<Question> getQuestionsByTopic(String topic) {
	return questionDao.getQuestionsByTopic(topic);
}

public List<Question> getAllQuestions() {
	return questionDao.getAllQuestions();
}

public Question getQuestionByQuestion(String question) {
	return questionDao.getQuestionByQuestion(question);
}

public void saveQuestion(Question question) {
	questionDao.saveQuestion(question);
}

public void deleteQuestion(String question) {
	questionDao.deleteQuestion(question);
}

public int saveResult(String username, Timestamp time, int score, String topic) {
	return questionDao.saveResult(username, time, score, topic);
}

public List<ResultHistory> getAllResultHistory(String username) {
	return questionDao.getAllResultHistory(username);
}

}