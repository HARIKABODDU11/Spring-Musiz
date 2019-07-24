package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;

    }
    @Override
    public User saveUser(User user) {
        User saveUser=userRepository.save(user);
        return saveUser;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    
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

    @Override

    public boolean deleteUser(int id) throws UserNotFoundException
    {
        Optional<User> user1 = userRepository.findById(id);

        if(!user1.isPresent())
        {
            throw new UserNotFoundException("Track Not Found");
        }

        try {

            userRepository.delete(user1.get());

            return true;

        }
        catch (Exception exception)
        {
            return false;
        }
    }
}
