package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;

import java.util.List;
//service interface
public interface UserService {
//methods
  public User saveUser(User user) throws UserAlreadyExistsException;

  public List<User> getAllUsers();
  public User updateUser(User user, int id) throws UserNotFoundException;

  public User deleteUser(int id) throws UserNotFoundException;


}
