package com.stackroute.MusixAppAssignment.service;

import com.stackroute.MusixAppAssignment.exceptions.TrackNotFoundException;
import com.stackroute.MusixAppAssignment.exceptions.UserAlreadyExistException;
import com.stackroute.MusixAppAssignment.model.Track;
import com.stackroute.MusixAppAssignment.model.TrackDTO;
import com.stackroute.MusixAppAssignment.repository.TrackRepository;

import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service  //it is a service class
public class TrackServiceImpl implements TrackService {

  //overriding all the methods from trackservice interface
  @Autowired
  private  TrackRepository trackRepository;
  private TrackMapper trackMapper;

  //creating the constructor
  public TrackServiceImpl(TrackRepository trackRepository,TrackMapper trackMapper) {

    this.trackRepository = trackRepository;
    this.trackMapper=trackMapper;
  }
  @Override
  public Track saveTrack(Track track) throws UserAlreadyExistException {

    if(trackRepository.existsById(track.getId())){

      throw new UserAlreadyExistException("track already exist");
    }
    TrackDTO trackDTO=trackMapper.trackToTrackDTO(track);
    TrackDTO trackDTO1=trackRepository.save(trackDTO);
    Track savedTrack=trackMapper.trackDTOToTrack(trackDTO1);
    if(savedTrack==null){
      throw new UserAlreadyExistException("track already exist");
    }
    return savedTrack;
  }

  @Override
  public List<Track> getAllTrack() {
    List<TrackDTO> trackDTOList=trackRepository.findAll();
    List<Track> trackList=trackMapper.trackDTOListToTrackList(trackDTOList);

    return  trackList;
  }

  @Override
  public Track updateTrack(Track track,int id) throws TrackNotFoundException {

    Optional<TrackDTO> trackDTOOptional= trackRepository.findById(id);

    if(!trackDTOOptional.isPresent()){

      throw new TrackNotFoundException("track not found exception");
    }
    Track track1=trackMapper.trackDTOToTrack(trackDTOOptional.get());

    track.setId(id);
    TrackDTO trackDTO=trackMapper.trackToTrackDTO(track1);
    TrackDTO savedTrackedDto=trackRepository.save(trackDTO);


    return  trackMapper.trackDTOToTrack(savedTrackedDto);

  }

  @Override
  public Track deleteTrack(int id){

    Optional<TrackDTO> trackDTOOptional = trackRepository.findById(id);
    trackRepository.delete(trackDTOOptional.get());
    return trackMapper.trackDTOToTrack(trackDTOOptional.get());


  }

//  @Override
//  public List<Track> trackByName(String name) {
//    List<Track> trackList= trackRepository.findTitleByName(name);
//    return trackList;
//  }
//
//  @Override
//  public List<Track> searchByNameAndId(int id, String name) {
//    List<Track> trackList1=trackRepository.findTitleByName(name,id);
//    return trackList1;
//  }


}
