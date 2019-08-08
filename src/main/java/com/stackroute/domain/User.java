package com.stackroute.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Document(collection = "Track")
@Data
@NoArgsConstructor
@AllArgsConstructor
// To ignore any unknown properties in JSON input without exception
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  //user properties
    private int id;
    private String firstName;
    private String lastName;
    private int age;


}



