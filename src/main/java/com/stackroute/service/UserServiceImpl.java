package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl implements UserService {

    UserRepository userRepository;
//Autowiring the muzix repository
    @Autowired
    
    
    
   //parametrized constructor
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;

    }
    
    //create the user
    @Override
    public User saveUser(User user) {
        User saveUser=userRepository.save(user);
        return saveUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    
    
     //delete the user data
    @Override
    public User deleteUser(int id) {
       return userRepository.deleteById(id);
    }

    //Updating the existing user
    @Override
    public User updateUser(User user) {
        Optional<User> user1 = userRepository.findById(user.getId());

        if (user1.isPresent()) {
            User newEntity = user1.get();
            newEntity.setId(user.getId());
            newEntity.setName(user.getName());
            newEntity.setUser(user.getTrack());
            newEntity = userRepository.save(newEntity);
            return newEntity;
        } else {
            user = userRepository.save(user);

            return user;
        }
    }
}
