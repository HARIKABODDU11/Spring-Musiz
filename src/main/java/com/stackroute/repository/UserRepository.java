package com.stackroute.repository;

import com.stackroute.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //it is saying as this class is repository class

public interface UserRepository extends JpaRepository<User,Integer> {

  //query for getting all users by name
  @Query(value = "SELECT * FROM User where name = ?1", nativeQuery = true )
  List<User> findTitleByName(String name);


  //getting users by searching
  @Query(value = "SELECT * FROM track where (?1 is null or name = ?1) and (?2 = 0 or id = ?2)", nativeQuery = true)
  List<User> findTitleByName(String name,int id);
}
