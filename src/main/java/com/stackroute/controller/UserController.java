package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Result;
import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.Root;
import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.List;

@RestController

public class UserController {
  //using the class userservice
  @Autowired
  private UserService userService;

  //create constructor for userservice
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("user")
  public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
    ResponseEntity responseEntity;
    userService.saveUser(user);
    responseEntity = new ResponseEntity<String>("row added successfully", HttpStatus.CREATED);
    return responseEntity;

  }

  @GetMapping("user")
  public ResponseEntity<?> getAllUser(@RequestBody User user) {

    //getting all tracks
    return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);

  }

  //get track by track,id and updated

  //getting track by id
  @PutMapping("/user/{id}")
  public ResponseEntity<?> getTrack(@RequestBody User user, @PathVariable("id") int id) throws Exception {

    userService.updateUser(user, id);
    return new ResponseEntity<String>("updated successfully", HttpStatus.CREATED);
  }

  //delete user by id
  @DeleteMapping("/user/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable int id) {
    userService.deleteUser(id);
    return new ResponseEntity<String>("deleted successfully", HttpStatus.OK);
  }

  //getting users by name
  @GetMapping("/user/{name}")
  public ResponseEntity<?> userByName(@PathVariable String name) {

    return new ResponseEntity<>(userService.userByName(name), HttpStatus.OK);
  }

  //getting users by searching name and id
  @GetMapping("/track/{id}/{name}")
  public ResponseEntity<?> searchByName(@PathVariable int id, @PathVariable String name) {
    return new ResponseEntity<>(userService.searchByNameAndId(id, name), HttpStatus.OK);

  }

  //getting list of user by last.fm
  @GetMapping("getLastFmTracks")
  public ResponseEntity<?> getLastFmTracks(@RequestParam String url) throws Exception{
    RestTemplate restTemplate = new RestTemplate();
    System.out.println(url);
    String string = restTemplate.getForObject(url,String.class);
    System.out.println("helo");
    ObjectMapper objectMapper = new ObjectMapper();
    Result result = objectMapper.readValue(string, Result.class);
    List<User> userList = result.results.usermatches.track;
    List<User> savedTrackList = new ArrayList<>();
    for (User user:userList) {
      User user1 = userService.saveUser(user);
      savedTrackList.add(user1);
    }
    return new ResponseEntity<>(savedTrackList,HttpStatus.OK);
  }
}
