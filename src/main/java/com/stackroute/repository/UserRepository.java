package com.stackroute.repository;

import com.stackroute.domain.User;
import com.stackroute.exceptions.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
//to identtify this as repository
public interface UserRepository extends MongoRepository<User,Integer> {
}
