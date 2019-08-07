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

public class UserServiceImpl implements UserService , ApplicationListener<ContextRefreshedEvent>, CommandLineRunner {

    @Value("${user.1.firstname:default}")
    String firstName1;
    @Value("${user.1.lastname}")
    String lastname1;
    @Value("${user.1.age}")
    int age1;


    @Value("${user.2.firstname:default}")
    String firstName2;
    @Value("${user.2.lastname}")
    String lastname2;
    @Value("${user.2.age}")
    int age2;
   @Autowired
    UserRepository userRepository;

   //service interface
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
//get all users
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
   //Deleting the user
    @Override
    public User deleteUser(int id) throws UserNotFoundException {
        if(!userRepository.existsById(id))
        {
            throw new UserNotFoundException("user not found");
        }
       return userRepository.deleteById(id);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("CommandLineRunner Implemented");
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        userRepository.save(new User(1,firstName1,lastname1,age1));
        userRepository.save(new User(1,firstName2,lastname2,age2));
    }
}
