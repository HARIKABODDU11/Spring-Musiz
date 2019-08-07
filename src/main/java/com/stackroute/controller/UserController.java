package com.stackroute.controller;


import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
//Controller class
public class UserController {
    //Creating object for the muzix service
    UserService userService;
//parameterized constructor
    public UserController(UserService userService) {
        this.userService = userService;
    }
//save the user
    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody User user) throws UserAlreadyExistsException {
            userService.saveUser(user);
            return new ResponseEntity<String>("Successfully created", HttpStatus.CREATED);
    }
// get all users
    @GetMapping("user")
    public ResponseEntity<?> getAllUsers() {
        userService.getAllUsers();
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
    }

//search the user by name
    @GetMapping("userByName")
    public ResponseEntity<?> userByName(@RequestParam String name) throws UserNotFoundException{
        userService.userByName(name);
        return  new ResponseEntity<String>("user by name", HttpStatus.OK);
    }



//uodate the user
    @PutMapping("user/{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable int id) throws   UserAlreadyExistsException {
        userService.updateUser(user, id);
        return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.CREATED);
    }
 //Deleting the user
    @DeleteMapping("user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) throws UserNotFoundException {
        userService.deleteUser(id);
        return new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
    }


}
