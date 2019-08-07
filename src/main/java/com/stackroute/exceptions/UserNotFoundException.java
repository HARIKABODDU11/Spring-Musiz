package com.stackroute.exceptions;


//Custom exception
public class UserNotFoundException extends Exception {
    //Display message
    private String message;
//Default and paramterized constructor
    public UserNotFoundException() {}
    public UserNotFoundException(String message){
        super(message);
        this.message=message;
    }
}

