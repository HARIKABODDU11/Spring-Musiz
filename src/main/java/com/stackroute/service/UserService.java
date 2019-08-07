package com.stackroute.service;

import com.stackroute.domain.User;

import java.util.List;
//Service interface
public interface UserService {
//methods in the interface
    public User saveUser(User user);
    public List<User> getAllUsers();
     public boolean deleteUser(int id);

    public User updateUser(User user);
    public List<User> userByName(String name);
}
