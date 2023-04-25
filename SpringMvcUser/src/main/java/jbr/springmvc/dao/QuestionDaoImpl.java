package jbr.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import jbr.springmvc.model.Question;
import jbr.springmvc.model.ResultHistory;


public class QuestionDaoImpl implements QuestionDao {

  @Autowired
  DataSource datasource;

  @Autowired
  JdbcTemplate jdbcTemplate;

  public int addQuestion(Question question) {
    String sql = "insert into questions values(?,?,?,?,?,?,?)";

    return jdbcTemplate.update(sql, new Object[] { question.getTopic(), question.getQuestion(), question.getOption1(),
        question.getOption2(), question.getOption3(), question.getOption4(),question.getAnswer()});
  }
  
  public List<String> getAllTopics() {
	    String sql = "SELECT DISTINCT topic FROM questions";
	    List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	    List<String> topics = new ArrayList<String>();
	    for (Map<String, Object> row : rows) {
	        String topic = (String) row.get("topic");
	        topics.add(topic);
	    }
	    return topics;
	}
  
  public List<Question> getQuestionsByTopic(String topic) {
      String sql = "SELECT DISTINCT * FROM questions WHERE topic = ? ORDER BY RAND() LIMIT 5";
      return jdbcTemplate.query(sql, new Object[]{topic}, new RowMapper<Question>() {
		@Override
		public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
		      Question question = new Question();
		      question.setTopic(rs.getString("topic"));
		      question.setQuestion(rs.getString("question"));
		      question.setOption1(rs.getString("option1"));
		      question.setOption2(rs.getString("option2"));
		      question.setOption3(rs.getString("option3"));
		      question.setOption4(rs.getString("option4"));
		      question.setAnswer(rs.getString("answer"));
		      return question;
		  }
	});
  }
  
  public List<Question> getAllQuestions() {
      String sql = "SELECT topic, question, option1, option2, option3, option4, answer FROM questions ORDER BY topic";
      List<Question> questions = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Question>(Question.class));
      return questions;
  }
  
  public Question getQuestionByQuestion(String question) {
	  String sql = "SELECT DISTINCT * FROM questions WHERE question = ?";
      return jdbcTemplate.queryForObject(sql, new Object[]{question}, new RowMapper<Question>() {
		@Override
		public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
		      Question question = new Question();
		      question.setTopic(rs.getString("topic"));
		      question.setQuestion(rs.getString("question"));
		      question.setOption1(rs.getString("option1"));
		      question.setOption2(rs.getString("option2"));
		      question.setOption3(rs.getString("option3"));
		      question.setOption4(rs.getString("option4"));
		      question.setAnswer(rs.getString("answer"));
		      return question;
		  }
	});
  }
  
  public void saveQuestion(Question question) {
      String sql = "UPDATE questions SET option1 = ?, option2 = ?, option3 = ?, option4 = ?, answer = ? WHERE question = ?";
      Object[] params = new Object[]{question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4(), question.getAnswer(), question.getQuestion()};
      jdbcTemplate.update(sql, params);
  }
  
  public void deleteQuestion(String question) {
	  String sql = "DELETE FROM questions WHERE question = ?";
      Object[] params = new Object[]{question};
      jdbcTemplate.update(sql, params);
  }
  
  public int saveResult(String username, Timestamp time, int score, String topic) {
	  String sql = "insert into results values(?,?,?,?)";
	  return jdbcTemplate.update(sql, new Object[] {username,time,score,topic});
  }
  
  public List<ResultHistory> getAllResultHistory(String username) {
	  String sql = "SELECT * FROM results where username = ?";
      return jdbcTemplate.query(sql, new Object[]{username}, new RowMapper<ResultHistory>() {
		@Override
		public ResultHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
		      ResultHistory result = new ResultHistory();
		      result.setUsername(rs.getString("username"));
		      result.setTime(rs.getTimestamp("time"));
		      result.setScore(rs.getInt("score"));
		      result.setTopic(rs.getString("topic"));
		      return result;
		  }
	});
  }
  
 }

 
