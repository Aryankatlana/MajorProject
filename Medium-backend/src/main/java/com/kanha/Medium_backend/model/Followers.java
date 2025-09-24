package com.kanha.Medium_backend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "followers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"follower_id", "following_id"})
)
public class Followers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;  // Primary key

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;  // User who follows

    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false)
    private User following;  // User being followed

    private LocalDateTime createdAt = LocalDateTime.now();  // Timestamp
}
