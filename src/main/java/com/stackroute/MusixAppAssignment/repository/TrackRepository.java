package com.stackroute.MusixAppAssignment.repository;

import com.stackroute.MusixAppAssignment.model.TrackDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository //it is saying as this class is repository class

public interface TrackRepository extends MongoRepository<TrackDTO,Integer> {

//      //query for geeting all traks by name
//    @Query(value = "SELECT * FROM track where name = ?1", nativeQuery = true )
//    List<TrackDTO> findTitleByName(String name);
//
//
//    //getting tracks by searching
//    @Query(value = "SELECT * FROM track where (?1 is null or name = ?1) and (?2 = 0 or id = ?2)", nativeQuery = true)
//    List<TrackDTO> findTitleByName(String name,int id);
}
