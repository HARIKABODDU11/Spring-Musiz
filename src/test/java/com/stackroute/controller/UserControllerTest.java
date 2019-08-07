package com.stackroute.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.User;

import com.stackroute.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import static org.mockito.ArgumentMatchers.any;//



//@RunWith(SpringRunner.class)
//@WebMvcTest
public class UserControllerTest {

  private MockMvc mockMvc;
  @InjectMocks
  private UserController userController;

  @Mock
  private UserService userService;



  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders
      .standaloneSetup(userController)
      .build();
  }

  @Test
  public void userController() throws Exception{
    User user= new User(1,"harika","boddu",21);
    when(userService.saveUser(user)).thenReturn(user);
    mockMvc.perform(post("/user")
      .contentType(MediaType.APPLICATION_JSON)
      .content(asJsonString(user)))
      .andExpect(status().isCreated());
    verify(userService, times(1)).saveUser(Mockito.any(User.class));
    verifyNoMoreInteractions(userService);
  }

  @Test
  public void setUserByName() throws Exception{
    List<User> user= Arrays.asList(new User(2,"hema","bethina",22));
    when(userService.userByName("user")).thenReturn(user);
    mockMvc.perform(get("/user/{name}", "user")
      .contentType(MediaType.APPLICATION_JSON)
      .content(asJsonString(user)))
      .andExpect(status().isOk());
    verify(userService, times(1)).userByName("user");
    verifyNoMoreInteractions(userService);
  }

  @Test
  public void deleteUserById() throws Exception{
    User user= new User(1,"harika","boddu",21);
    doNothing().when(userService).deleteUser(user.getId());
    mockMvc.perform(delete("/user/{id}",1)
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk());

    verify(userService,times(1)).deleteUser(user.getId());
    verifyNoMoreInteractions(userService);

  }

//  @Test
//  public void UpdateTrackTest() throws Exception{
//    User user=new User(1,"harika","boddu",21);
//    when(userService.updateUser(user,1)).thenReturn(user);
//    mockMvc.perform(put("/user/{id}",1)
//      .contentType(MediaType.APPLICATION_JSON)
//      .content(asJsonString(user)))
//      .andExpect(status().isCreated());
//    verify(userService,times(1)).updateUser(user,1);
//    verifyNoMoreInteractions(userService);
//  }
  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
