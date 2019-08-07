package com.stackroute.service;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserAlreadyExistsException;
import com.stackroute.exceptions.UserNotFoundException;
import com.stackroute.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import javax.sound.midi.Track;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class UserServiceImplTest {

  User user;
  //Create a mock for UserRepository
  @Mock
  UserRepository userRepository;

  //Inject the mocks as dependencies into UserServiceImpl
  @InjectMocks
  UserServiceImpl userService;
  List<User> list= null;


  @Before
  public void setUp(){
    //Initialising the mock object
    MockitoAnnotations.initMocks(this);
    user=new User(1,"harika","boddu",21);

  }

  @Test
  public void saveUserTestSuccess() throws UserAlreadyExistsException {

    when(userRepository.save((User)any())).thenReturn(user);
    User savedUser = userService.saveUser(user);
    Assert.assertEquals(user,savedUser);

    //verify here verifies that userRepository save method is only called once
    verify(userRepository,times(1)).save(user);

  }


  @Test(expected = UserAlreadyExistsException.class)
  public void saveUserTestFailure() throws UserAlreadyExistsException {
    when(userRepository.save((User) any())).thenReturn(null);
    User savedUser = userService.saveUser(user);
    System.out.println("savedUser" + savedUser);

  }

  @Test
  public void getAllTracksTest() throws Exception{
    List<User> userList= Arrays.asList(new User(1,"harika","boddu",21),
      new User(2,"hema","bethina",22));
    when(userRepository.findAll()).thenReturn(userList);
    List<User> userList1=userService.getAllUsers();
    Assert.assertEquals(userList,userList1);
    verify(userRepository,times(1)).findAll();
  }

  @Test
  public void updateUserTest() throws Exception{
    User user1=new User(2,"hema","bethina",22);
    Optional<User> optional=Optional.of(user1);
    when(userRepository.save(user1)).thenReturn(user1);
    when(userRepository.findById(user1.getId())).thenReturn(optional);
    User user2=userService.updateUser(user1,2);
    Assert.assertEquals(user1,user2);
    verify(userRepository,times(1)).save(user2);
  }

}
