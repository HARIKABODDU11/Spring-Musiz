package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;

import java.util.List;
//Service interface
public interface UserService {
//methods in the interface
    public User saveUser(User user) throws UserAlreadyExistsException;
    public List<User> getAllUsers();
    public List<User> userByName(String name) throws UserNotFoundException;
    public User deleteUser(int id) throws TrackNotFoundException;

    public User updateUser(User user);
}
