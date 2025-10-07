package com.kanha.Medium_backend.Repository;

import com.kanha.Medium_backend.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ArticleRepo extends JpaRepository<Article, UUID> {

}
