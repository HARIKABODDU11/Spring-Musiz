package com.stackroute.exceptions;

public class UserAlreadyExistsException extends Exception {
    private String message;
   //parametrized and non parametrized constructor
    public UserAlreadyExistsException() {}
    public UserAlreadyExistsException(String message){
        super(message);
        this.message=message;
    }


}
