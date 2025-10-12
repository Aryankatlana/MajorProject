package com.kanha.Medium_backend.controller;

import com.kanha.Medium_backend.Service.UserService;
import com.kanha.Medium_backend.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserApi {

    @PostConstruct
    public void init() {
        System.out.println("userService = " + userService);
    }

    @Autowired
    private UserService userService;

    //Listing out all the users
    //already added the security in SERVICE Class
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/profile")
    public ResponseEntity<List<User>>  getProfileAllUsers(){
        return userService.getProfileAllUsers();
    }

    //Get User by ID
    @GetMapping("profile/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id){
        return new ResponseEntity<>(userService.getProfileById(id).getBody(), userService.getProfileById(id).getStatusCode());
    }

    //adding the user
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("profile")
    public ResponseEntity<?> addUser(@RequestBody User user){
        return userService.addUser(user);
    }


    //update the user by passing the id
//    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("profile/{id}")
    public ResponseEntity<?> updateProfile(@RequestBody User user, @PathVariable UUID id){
        ResponseEntity<?> user1 = userService.updateUser(user, id);
        return new ResponseEntity<>(user1.getBody(), user1.getStatusCode()); //get body send you the message
    }

    //delete the user by specific id
//    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("profile/{id}")
    public ResponseEntity<?> DeleteUserById(@PathVariable UUID id){
        return userService.deleteUserByID(id);
    }
}
