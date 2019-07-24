package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;

    }
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

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> userByName(String name) throws UserNotFoundException {

        List<User> user=userRepository.userByName(name);
        if (user.isEmpty())
        {
            throw new UserNotFoundException("User not found");
        }
        return  user;
    }

@Override
    public User updateUser(User user, int id) throws UserAlreadyExistsException {
        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())
            throw new UserAlreadyExistsException("user id not found");


        user.setId(id);

        return userRepository.save(user);

    }

    @Override
    public boolean deleteUser(int id) throws UserNotFoundException {


        Optional<User> userOptional = userRepository.findById(id);

        if (!userOptional.isPresent())
            throw new UserNotFoundException("user id not found");

        userRepository.delete(userOptional.get());
        return true;
    }


}
