package com.kanha.Medium_backend.controller;

import com.kanha.Medium_backend.Service.ArticleService;
import com.kanha.Medium_backend.model.Article;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/article")
public class ArticleApi {

    private final ArticleService articleService;

    public ArticleApi(ArticleService articleService) {
        this.articleService = articleService;
    }

    //Get All articles
    @GetMapping
    public ResponseEntity<List<Article>> getAllArticles(){
        List<Article> article = articleService.getAllArticles();
        if(article.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok(article);
        }
    }

    //Find by id
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleByID(@PathVariable UUID id){
        Article article = articleService.getArticleByID(id);
        return ResponseEntity.ok(article);
    }

    //Add New Article
    @PostMapping()
    public ResponseEntity<Article> addArticle(@RequestBody Article article){
        Article saved = articleService.createArticle(article);
        return ResponseEntity.status(201).body(saved);
    }

    //Update Article By id
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable UUID id , @RequestBody Article article){
        Article update = articleService.updateArticle(id,article);
        return ResponseEntity.ok(update);
    }

    //Delete Article by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable UUID id){
        boolean delete = articleService.deleteArticle(id);
        if(!delete){
            return ResponseEntity.status(404).body("Article not found with id" +id);
        }
        else{
            return ResponseEntity.ok("Article Deleted Successfully");
        }
    }

}
