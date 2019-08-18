package com.stackroute.MusixAppAssignment.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
//this annotation is used to create a table
@Data
@Builder
@NoArgsConstructor  //it creates constructor with out any arguments
@AllArgsConstructor   //it creates constructor with arguments
@Getter  //create geeters
@Setter  //it creates setters

//it ignores all unwanted data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrackDTO {
 @Id  //representing id as a primary key
// @GeneratedValue(strategy=GenerationType.AUTO)  //generating automatic values
//  @Column(name = "id", updatable = false, nullable = false)
  private int id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getArtist() {
    return artist;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getStreamable() {
    return streamable;
  }

  public void setStreamable(String streamable) {
    this.streamable = streamable;
  }

  public int getListeners() {
    return listeners;
  }

  public void setListeners(int listeners) {
    this.listeners = listeners;
  }

  private String name;

  private String artist;

  private String url;
  private String streamable;
  private int listeners;



}
