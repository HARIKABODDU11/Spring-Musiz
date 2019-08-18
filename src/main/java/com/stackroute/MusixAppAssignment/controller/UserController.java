package com.stackroute.MusixAppAssignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.MusixAppAssignment.exceptions.TrackNotFoundException;
import com.stackroute.MusixAppAssignment.model.Root;
import com.stackroute.MusixAppAssignment.model.Track;
import com.stackroute.MusixAppAssignment.service.TrackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

//we are configuring the rest controller
@CrossOrigin(origins = "*")
@RestController
public class UserController {
  private final Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

  //using the class trackservice
    @Autowired
    private TrackService trackService;

    //create constructor for trackservice
    public UserController(TrackService trackService) {
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track) throws Exception {
        ResponseEntity responseEntity;
        Track savedTrack = trackService.saveTrack(track);
      logger.info("This is an info message");
        responseEntity = new ResponseEntity<Track>(savedTrack, HttpStatus.CREATED);
        return responseEntity;

    }

    @GetMapping("track")
    public ResponseEntity<?> getAllTrack() {

        //getting all tracks
        return new ResponseEntity<List<Track>>(trackService.getAllTrack(), HttpStatus.OK);

    }

    //get track by track,id and updated

    //getting track by id
    @PutMapping("/track/{id}")
    public ResponseEntity<?> getTrack(@RequestBody Track track, @PathVariable("id") int id) throws Exception {

        Track track1=trackService.updateTrack(track, id);
        return new ResponseEntity<Track>(track1, HttpStatus.CREATED);
    }

    //delete track by id
    @DeleteMapping("/track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable int id)  {
        return new ResponseEntity<>(trackService.deleteTrack(id), HttpStatus.OK);
    }

//    //getting tracks by name
//    @GetMapping("/track/{name}")
//    public ResponseEntity<?> trackByName(@PathVariable String name) {
//
//        return new ResponseEntity<>(trackService.trackByName(name), HttpStatus.OK);
//    }
//
//    //getting tracks by searching name and id
//    @GetMapping("/track/{id}/{name}")
//    public ResponseEntity<?> searchByName(@PathVariable int id, @PathVariable String name) {
//        return new ResponseEntity<>(trackService.searchByNameAndId(id, name), HttpStatus.OK);
//
//    }

    //getting list of tack by last.fm
    @GetMapping("listOfTrack")
    public ResponseEntity<?> getLastFmTracks(@RequestParam String url) throws Exception {

        //getting all tracks in json formate
        RestTemplate restTemplate = new RestTemplate();
        String string = restTemplate.getForObject(url,String.class);
        System.out.println(string);

        //converting the json object to java object
        ObjectMapper objectMapper = new ObjectMapper();
        Root root = objectMapper.readValue(string, Root.class);
        List<Track> trackList = root.getResults().getTrackmatches().getTrack();
        System.out.println(trackList);
        List<Track> savedTrackList = new ArrayList<>();

        //getting list of tracks by using for loop
        for (Track user:trackList) {
            Track user1 = trackService.saveTrack(user);
            savedTrackList.add(user1);
        }
        return new ResponseEntity<>(savedTrackList,HttpStatus.OK);
    }
}
