package jbr.springmvc.dao;

import jbr.springmvc.model.Login;
import jbr.springmvc.model.LoginStudent;
import jbr.springmvc.model.Student;
import jbr.springmvc.model.User;

public interface UserDao {

  int register(User user);

  User validateUser(Login login);
  
  int registerStudent(Student student);
  
  Student validateStudent(LoginStudent login);
}