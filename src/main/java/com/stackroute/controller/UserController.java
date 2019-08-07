package com.stackroute.controller;


import com.stackroute.domain.User;
import com.stackroute.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
////Controller class
public class UserController {
    //Creating object for the user service
    UserService userService;
    //Constructor
    public UserController(UserService userService){
        this.userService=userService;
    }
     //Saving the track
    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        ResponseEntity responseEntity;
        try{
            userService.saveUser(user);
            responseEntity=new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
     //Retrieving all the users
    @GetMapping("user")
    public ResponseEntity<?> getAllUsers(){
        ResponseEntity responseEntity;
        try{
            responseEntity=new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);
        }
        catch (Exception ex){
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    
    
    
    //Deleting the user
    @DeleteMapping("user")
    public ResponseEntity<?> deleteUser(@RequestBody User user) {

        return new ResponseEntity<>(userService.deleteUser(user.getId()), HttpStatus.OK);
    }

    //Updiatng the track
    @PutMapping("user")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(muzix), HttpStatus.OK);
    }
}
