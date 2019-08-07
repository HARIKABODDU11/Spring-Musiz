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
    
    // parameter constructor
   
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;

    }
    //save the user data
    @Override
    public User saveUser(User user) {
        User saveUser=userRepository.save(user);
        return saveUser;
    }
  //retrieve the users data
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    //Updating the existing user
    @Override
    public User updateUser(User user, int id) throws UserNotFoundException
        {
            Optional<User> track1 = userRepository.findById(id);

            if(!track1.isPresent())
            {
                throw new UserNotFoundException("Track Not Found");
            }

            user.setId(id);

            User savedUser = userRepository.save(user);
            System.out.println(savedUser.getLastName());
            return savedUser;
        }

     //Deleting the user
    @Override
    public User deleteUser(int id) {
        return userRepository.deleteById(id);
    }

}
