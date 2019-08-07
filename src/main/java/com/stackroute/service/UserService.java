package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {

  //creating methods

  User saveUser(User user) throws UserAlreadyExistsException;

  List<User> getAllUsers();

  User updateUser(User user, int id) throws UserNotFoundException;

  User deleteUser(int id);

  List<User> userByName(String name);

  List<User> searchByNameAndId(int id,String name);

}
