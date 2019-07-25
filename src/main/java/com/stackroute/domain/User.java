package com.stackroute.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.Access;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name="User")
@NoArgsConstructor
@AllArgsConstructor
@PropertySource("com.stackroute.resource")
public class User {

    @Id
    private int id;
    private String firstName;
    private String lastName;
    private int age;


}