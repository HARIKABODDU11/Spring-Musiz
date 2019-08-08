package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImpl implements UserService {


  @Autowired
    UserRepository userRepository;

  //constructor
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;

    }
    //save the user
    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if(userRepository.existsById(user.getId())){
            throw new UserAlreadyExistsException("User Already Exists");
        }
        User saveUser=userRepository.save(user);
        if(saveUser==null){
            throw new UserAlreadyExistsException("User already exists");
        }
        return saveUser;
    }
//get all users
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


// updtae the user data
@Override
    public User updateUser(User user, int id) throws UserNotFoundException {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())
            throw new UserNotFoundException("user id not found");


        user.setId(id);

        return userRepository.save(user);

    }
  // delete the user data
  public User deleteUser(int id) throws UserNotFoundException
  {
    Optional<User> user1 = userRepository.findById(id);

    if(!user1.isPresent())
    {
      throw new UserNotFoundException("Track Not Found");
    }

    try {

      userRepository.delete(user1.get());

      return user1.get();

    }
    catch (Exception exception)
    {
      return null;
    }
  }

}
