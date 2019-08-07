package com.stackroute.service;

import com.stackroute.domain.User;

import java.util.List;
////Service interface
public interface UserService {
// methods in the interface
    public User saveUser(User user);
    public List<User> getAllUsers();
    public User updateUser(User user, int id) throws UserNotFoundException;
    public User deleteUser(int id) throws UserNotFoundException;
}
