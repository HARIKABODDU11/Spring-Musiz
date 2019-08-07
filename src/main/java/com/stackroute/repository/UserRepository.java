package com.stackroute.repository;

import com.stackroute.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
//Repository which extends the Jpa Repository
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
