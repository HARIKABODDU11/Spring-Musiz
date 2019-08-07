package com.stackroute.exceptions;


//Custom exception

public class UserAlreadyExistsException extends Exception {
      //Display message
    private String message;
 //Default and parameterize constructors
    public UserAlreadyExistsException() {}
    public UserAlreadyExistsException(String message){
        super(message);
        this.message=message;
    }


}
