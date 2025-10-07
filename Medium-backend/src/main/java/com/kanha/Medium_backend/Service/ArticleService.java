package com.kanha.Medium_backend.Service;

import com.kanha.Medium_backend.Repository.ArticleRepo;
import com.kanha.Medium_backend.model.Article;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {
    private ArticleRepo articleRepo;

    public ArticleService(ArticleRepo articleRepo){
        this.articleRepo = articleRepo;
    }

    //Get Article by id
    public Article getArticleByID(UUID id) {
        return articleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id " + id));
    }

    //Add new Article
    public Article createArticle(Article article){
        return articleRepo.save(article);
    }

    //Get All Article
    public List<Article> getAllArticles(){
        return articleRepo.findAll();
    }

    //update Article
    public Article updateArticle(UUID id , Article updateArticle){
        Article article = articleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found with id " + id));
        article.setContent(updateArticle.getContent());
        article.setTags(updateArticle.getTags());
        article.setTitle(updateArticle.getTitle());
        return articleRepo.save(article);
    }

    //Delete Article by id
    public boolean deleteArticle(UUID id) {
        return articleRepo.findById(id).map(article -> {
            articleRepo.delete(article);
            return true;
        }).orElse(false);
    }
}
