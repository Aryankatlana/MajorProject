package com.kanha.Medium_backend.Repository;

import com.kanha.Medium_backend.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface AdminRepo extends JpaRepository<Admin, UUID> {
}

