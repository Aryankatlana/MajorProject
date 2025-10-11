package com.kanha.Medium_backend.Service;

import com.kanha.Medium_backend.Repository.ArticleRepo;
import com.kanha.Medium_backend.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    ArticleRepo articleRepo;

    public void addArticle(Article article) {
        System.out.println("Article added to the DB");
        articleRepo.save(article);
    }

    //get article of Specific user By Id
    public List<Article> getArticles() {
        System.out.println("All article got suxsfully");
        return articleRepo.findAll();
    }
}
