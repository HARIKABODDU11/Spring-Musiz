package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl implements UserService {
    @Autowired
     //Autowiring the muzix repository
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
// get all the users data
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
//search the data by name
    @Override
    public List<User> userByName(String name) {

        return userRepository.userByName(name);
    }
    
    
     //Deleting the user
    @Override
    public boolean deleteUser(int id) {
        userRepository.deleteById(id);
        return true;
    }

    //Updating the existing user
    @Override
    public Muzix updateUser(User user) {
        Optional<User> user1 = userRepository.findById(user.getId());

        if (user1.isPresent()) {
            User newEntity = user1.get();
            newEntity.setId(user.getId());
            newEntity.setName(user.getName());
            newEntity.setTrack(user.getTrack());
            newEntity = userRepository.save(newEntity);
            return newEntity;
        } else {
            user = userRepository.save(user);

            return user;
        }
    }


}
