package com.stackroute.repository;

import com.stackroute.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    User user;

    @Before
    public void setUp() {
        user = new User(1, "harika", "boddu", 22);
    }

    @After
    public void tearDown() {

        userRepository.deleteAll();
    }
  @Test
  public void testSaveTrack(){
    userRepository.save(user);
    User fetchTrack = userRepository.findById(this.user.getId()).get();
    Assert.assertEquals(1,fetchTrack.getId());
  }


  @Test
  public void getAllUsers()
  {
    List<User> users = new ArrayList<>();
    User user1 = new User(1,"harika","boddu",21);
    User user2 = new User(2,"hema","betina",22);
    users.add(user1);
    users.add(user2);
    List<User> userList = userRepository.findAll();
    Assert.assertEquals(users,userList);
  }


}
