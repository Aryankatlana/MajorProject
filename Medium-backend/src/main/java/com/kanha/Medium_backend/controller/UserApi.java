package com.kanha.Medium_backend.controller;

import com.kanha.Medium_backend.Exception.UserNotFoundException;
import com.kanha.Medium_backend.Service.UserService;
import com.kanha.Medium_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("profile")
    private ResponseEntity<List<User>>  getProfileAllUsers(){
        List<User> user = userService.getProfileAllUsers().getBody();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("profile")
    private void addUser(@RequestBody User user){
        userService.addUser(user);
        System.out.println("Added");
    }


    @PutMapping("profile")
    private ResponseEntity<?> updateProfile(@RequestBody User user){
        userService.updateUser(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //delete the user
    @DeleteMapping("profile/{id}")
    private void DeleteUSerById(){

    }


}
