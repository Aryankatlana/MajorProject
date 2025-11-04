package com.kanha.Medium_backend.Service;

import com.kanha.Medium_backend.Repository.UserRepo;
import com.kanha.Medium_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    UserRepo repo;

    //proving user's id
    public UUID providingUserId(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = repo.getByUsername(username);
        return user.getId();
    }
}
