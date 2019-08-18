package com.stackroute.MusixAppAssignment.service;

import com.stackroute.MusixAppAssignment.model.Track;
import com.stackroute.MusixAppAssignment.model.TrackDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackMapper {
  TrackDTO trackToTrackDTO(Track track);
  Track trackDTOToTrack(TrackDTO trackDTO);
  List<Track> trackDTOListToTrackList(List<TrackDTO> trackDTOList);
}
