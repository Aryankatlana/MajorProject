package com.kanha.Medium_backend.Repository;

import com.kanha.Medium_backend.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ArticleRepo extends JpaRepository<Article, UUID> {
    List<Article> findAllById(UUID id);
}
