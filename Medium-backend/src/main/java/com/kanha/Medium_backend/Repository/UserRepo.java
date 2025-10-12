package com.kanha.Medium_backend.Repository;

import com.kanha.Medium_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
    User getByUsername(String username);
}
