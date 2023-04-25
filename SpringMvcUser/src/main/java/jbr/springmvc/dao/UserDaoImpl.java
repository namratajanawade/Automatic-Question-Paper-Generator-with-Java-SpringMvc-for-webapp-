package jbr.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import jbr.springmvc.model.Login;
import jbr.springmvc.model.LoginStudent;
import jbr.springmvc.model.Student;
import jbr.springmvc.model.User;

public class UserDaoImpl implements UserDao {

  @Autowired
  DataSource datasource;

  @Autowired
  JdbcTemplate jdbcTemplate;

  public int register(User user) {
    String sql = "insert into users values(?,?,?,?,?)";

    return jdbcTemplate.update(sql, new Object[] { user.getUsername(), user.getPassword(), user.getFirstname(),
        user.getLastname(), user.getEmail()});
  }

  public User validateUser(Login login) {
    String sql = "select * from users where username='" + login.getUsername() + "' and password='" + login.getPassword()
        + "'";
    List<User> users = jdbcTemplate.query(sql, new UserMapper());

    return users.size() > 0 ? users.get(0) : null;
  }


  public int registerStudent(Student student) {
	  String sql = "insert into students values(?,?,?,?,?)";

	  return jdbcTemplate.update(sql, new Object[] { student.getUsername(), student.getPassword(), student.getFirstname(),
	        student.getLastname(), student.getEmail()});
  }


  public Student validateStudent(LoginStudent login) {
	  String sql = "select * from students where username='" + login.getUsername() + "' and password='" + login.getPassword()
      + "'";
	  List<Student> users = jdbcTemplate.query(sql, new StudentMapper());

	  return users.size() > 0 ? users.get(0) : null;
}

}

class UserMapper implements RowMapper<User> {

  public User mapRow(ResultSet rs, int arg1) throws SQLException {
    User user = new User();

    user.setUsername(rs.getString("username"));
    user.setPassword(rs.getString("password"));
    user.setFirstname(rs.getString("firstname"));
    user.setLastname(rs.getString("lastname"));
    user.setEmail(rs.getString("email"));

    return user;
  }
}

class StudentMapper implements RowMapper<Student> {

	  public Student mapRow(ResultSet rs, int arg1) throws SQLException {
	    Student user = new Student();

	    user.setUsername(rs.getString("username"));
	    user.setPassword(rs.getString("password"));
	    user.setFirstname(rs.getString("firstname"));
	    user.setLastname(rs.getString("lastname"));
	    user.setEmail(rs.getString("email"));

	    return user;
	  }
	}