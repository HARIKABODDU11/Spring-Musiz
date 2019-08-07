package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl implements UserService {
     @Autowired
 //Autowiring the muzix repository
    UserRepository userRepository;

   
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
//retrieving all the users
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
  //search userbyname
    @Override
    public List<User> userByName(String name) throws UserNotFoundException {

        List<User> user=userRepository.userByName(name);
        if (user.isEmpty())
        {
            throw new UserNotFoundException("User not found");
        }
        return  user;
    }
    
    
    
    
     //Deleting the user
    @Override
    public User deleteUser(int id) throws UserNotFoundException {
        if(!userRepository.existsById(id))
        {
            throw new UserNotFoundException("user not found");
        }
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
            newEntity.setTrack(user.getTrack());
            newEntity = userRepository.save(newEntity);
            return newEntity;
        } else {
            user = userRepository.save(user);

            return user;
        }
    }


}
