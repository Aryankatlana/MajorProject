package com.kanha.Medium_backend.Service;

import com.kanha.Medium_backend.Repository.UserRepo;
import com.kanha.Medium_backend.model.User;
import com.kanha.Medium_backend.model.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repo.getByUsername(username);

        if(user == null) {
            System.out.println("User is not in Database");
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(user); //we are getting new user with its details via UserPrincipal
    }
}
