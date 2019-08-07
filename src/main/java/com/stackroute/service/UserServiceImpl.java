package com.stackroute.service;


import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  //it is a service class
public class UserServiceImpl implements UserService {

  //overriding all the methods from userservice interface
  @Autowired
  private UserRepository userRepository;

  //creating the constructor
  public UserServiceImpl(UserRepository userRepository) {

    this.userRepository = userRepository;
  }

  @Override
  public User saveUser(User user) throws UserAlreadyExistsException {

    if(userRepository.existsById(user.getId())){

      throw new UserAlreadyExistsException("user already exist");
    }

    User user1=userRepository.save(user);
    if(user1==null){
      throw new UserAlreadyExistsException("user already exist");
    }
    return user1;
  }

  @Override
  public List<User> getAllUsers() {

    return userRepository.findAll();
  }

  @Override
  public User updateUser(User user,int id) throws UserNotFoundException {

    Optional<User> user1= userRepository.findById(id);

    if(!user1.isPresent()){

      throw new UserNotFoundException("user not found exception");
    }
    if(user == null){
      throw new UserNotFoundException("user not found exception");
    }
    user.setId(id);

    return  userRepository.save(user);

  }

  @Override
  public User deleteUser(int id) {

    Optional<User> user = userRepository.findById(id);
    userRepository.deleteById(id);
    return user.get();

  }

  @Override
  public List<User> userByName(String name) {
    List<User> userList= userRepository.findTitleByName(name);
    return userList;
  }

  @Override
  public List<User> searchByNameAndId(int id, String name) {
    List<User> userList1=userRepository.findTitleByName(name,id);
    return userList1;
  }


}
