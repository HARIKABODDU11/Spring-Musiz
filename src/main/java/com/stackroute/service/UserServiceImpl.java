package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl implements UserService {
     //Autowiring the muzix repository
  @Autowired
    UserRepository userRepository;

  //constructor
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;

    }
    //save the user
    @Override
    public User saveUser(User user) {
        User saveUser=userRepository.save(user);
        return saveUser;
    }
//get data of all users
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    
    
     //Deleting the user
    @Override
    public boolean deleteUser(int id) {
        userRepository.deleteById(id);
        return true;
    }
      //Tracking the data by userbyname
    @Override
    public List<User> userByName(String name)
    {

        return userRepository.userByName(name);
    }


    //Updating the existing user
    @Override
    public User updateUser(User user) {
        Optional<User> user1 = userRepository.findById(user.getId());

        if (user1.isPresent()) {
            User newEntity = user1.get();
            newEntity.setId(muzix.getId());
            newEntity.setName(muzix.getName());
            newEntity.setTrack(muzix.getTrack());
            newEntity = userRepository.save(newEntity);
            return newEntity;
        } else {
            user = userRepository.save(user);

            return user;
        }
    }



}
