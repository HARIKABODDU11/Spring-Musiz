package com.stackroute.repository;

import com.stackroute.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class UserRepositoryTest {
  @Autowired
  private UserRepository userRepository;
  private User user;
  //triggers before every test
  @Before
  public void setUp()
  {
    user = new User(1,"harika","boddu",21);
  }
  //triggers after every test
  @After
  public void tearDown(){
    userRepository.deleteAll();
  }

  @Test
  public void testGetAllUser(){
    User u1=new User(1,"harika","boddu",21);
    userRepository.save(u1);

    List<User> list = userRepository.findAll();
    Assert.assertEquals(u1,list.get(1));


  }
}
