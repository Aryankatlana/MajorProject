package com.kanha.Medium_backend.Service;

import com.kanha.Medium_backend.Exception.UserNotFoundException;
import com.kanha.Medium_backend.Repository.UserRepo;
import com.kanha.Medium_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void getProfileById() {

    }

    public ResponseEntity<List<User>> getProfileAllUsers(){
        try {
            List<User> users = userRepo.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public void updateUser(User user) {
        User currentUser = new User();
        currentUser.setId(user.getId());
        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(user.getPassword());
        currentUser.set_verified(true);
        currentUser.setRole(user.getRole());
        currentUser.setAvatar(user.getAvatar());
        currentUser.setBio(user.getBio());
        userRepo.save(currentUser);
    }

    public void addUser(User user) {
        userRepo.save(user);
        System.out.println("Added to the repo through service");
    }
}
