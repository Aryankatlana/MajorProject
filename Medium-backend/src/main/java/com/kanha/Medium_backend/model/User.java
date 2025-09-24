package com.kanha.Medium_backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, length = 50)
    private String username;

    @Column(unique = true, nullable = false, length = 100)
    private String email;


    @Column(nullable = false, length = 8)

    private String password;

    private boolean is_verified = false;


    @Enumerated(EnumType.STRING) //If we don't use this, It take role as integer like 0,1,2,...
    private Role role = Role.USERS;

    private String avatar;

    private String bio;

    @Column(updatable = false)
    private LocalDateTime created_at;

    private LocalDateTime updated_at;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Article> articles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Like> likes;

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    // Automatically set created_at and updated_at on insert
    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    // Automatically update updated_at on any update
    @PreUpdate
    protected void onUpdate() {
        this.updated_at = LocalDateTime.now();
    }
}
