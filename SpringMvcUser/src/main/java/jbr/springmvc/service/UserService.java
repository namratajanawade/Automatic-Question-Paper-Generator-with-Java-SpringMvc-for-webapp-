package jbr.springmvc.service;

import jbr.springmvc.model.Login;
import jbr.springmvc.model.User;
import jbr.springmvc.model.Student;
import jbr.springmvc.model.LoginStudent;

public interface UserService {

  int register(User user);

  User validateUser(Login login);
  
  int registerStudent(Student student);
  
  Student validateStudent(LoginStudent login);
}