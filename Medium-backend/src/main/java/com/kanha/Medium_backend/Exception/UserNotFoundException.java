package com.kanha.Medium_backend.Exception;

public class UserNotFoundException extends Throwable {

    //constructor for this class
    public UserNotFoundException(Exception e) {
        System.out.println("Something is wrong while fetching the data");
    }
}
