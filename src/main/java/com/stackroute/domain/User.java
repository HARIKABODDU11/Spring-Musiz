package com.stackroute.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

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

public class User {
  @Id  //representing id as a primary key
  @GeneratedValue(strategy= GenerationType.AUTO)  //generating automatic values
  @Column(name = "id", updatable = false, nullable = false)
    private int id;
    private String firstName;
    private String lastName;
    private int age;
}


