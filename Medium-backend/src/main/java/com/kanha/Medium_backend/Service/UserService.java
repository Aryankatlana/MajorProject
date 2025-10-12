package com.kanha.Medium_backend.Service;

import com.kanha.Medium_backend.Repository.UserRepo;
import com.kanha.Medium_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    //getting the user by it's id
    public ResponseEntity<User> getProfileById(UUID id){
        User user = userRepo.findById(id).orElse(null);
        if(user != null)
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        else
            return new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
    }

    //getting all the users
    public ResponseEntity<List<User>> getProfileAllUsers(){
        List<User> users = userRepo.findAll();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK); // 200 OK with users
        }
    }

    public ResponseEntity<?> updateUser(User user, UUID id) {
       User user1 = userRepo.getReferenceById(id);

       user1.setRole(user.getRole());
       user1.setAvatar(user.getAvatar());
       user1.setBio(user.getBio());
       user1.setUsername(user.getUsername());
       user1.setEmail(user.getEmail());
       user1.setPassword(user.getPassword());
       user1.setCreated_at(LocalDateTime.now());
       user1.setRole(user.getRole());
       user1.set_verified(true);

        try {
            userRepo.save(user1);
            return new ResponseEntity<>("Successfully Updated", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Fill the Required fields properly",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> addUser(User user) {
        try {
            userRepo.save(user);
            return new ResponseEntity<>("Added to the repo through service", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Please Fill proper data", HttpStatus.BAD_REQUEST);
        }
    }

    //to delete the user
    public ResponseEntity<?> deleteUserByID(UUID id) {
        //if user is not null then delete otherwise httpStatus Not Found
        User user = userRepo.getReferenceById(id);

        if(user != null) {
            userRepo.delete(user);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
