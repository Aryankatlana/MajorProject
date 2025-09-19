package com.kanha.Medium_backend.controller;

import com.kanha.Medium_backend.Service.UserService;
import com.kanha.Medium_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserApi {

    @Autowired
    private UserService userService;

    //Listing out all the users
    @GetMapping("profile")
    private ResponseEntity<List<User>>  getProfileAllUsers(){
        List<User> user = userService.getProfileAllUsers().getBody();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //Get User by ID
    @GetMapping("profile/{id}")
    private ResponseEntity<User> getUserById(@PathVariable UUID id){
        return new ResponseEntity<>(userService.getProfileById(id).getBody(), userService.getProfileById(id).getStatusCode());
    }

    //adding the user
    @PostMapping("profile")
    private ResponseEntity<?> addUser(@RequestBody User user){
        return new ResponseEntity<>("Created", userService.addUser(user).getStatusCode());
    }


    //update the user by passing the id
    @PutMapping("profile/{id}")
    private ResponseEntity<?> updateProfile(@RequestBody User user, @PathVariable UUID id){
        ResponseEntity<?> user1 = userService.updateUser(user, id);
        return new ResponseEntity<>(user1.getBody(), user1.getStatusCode()); //get body send you the message
    }

    //delete the user by specific id
    @DeleteMapping("profile/{id}")
    private ResponseEntity<?> DeleteUSerById(@PathVariable UUID id){
        return new ResponseEntity<>("Deleted",userService.deleteUserByID(id).getStatusCode());
    }


}
