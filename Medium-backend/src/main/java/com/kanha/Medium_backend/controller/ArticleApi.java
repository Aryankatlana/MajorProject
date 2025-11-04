package com.kanha.Medium_backend.controller;

import com.kanha.Medium_backend.Service.ArticleService;
import com.kanha.Medium_backend.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/article")
public class ArticleApi {

    @Autowired
    ArticleService articleService;

    //listing all the articles
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/get")
    public List<Article> getAllArticle(){
        return articleService.getArticles();
    }

    //create the article
    @PreAuthorize("hasAnyAuthority('USERS')")
    @PostMapping("/add")
    public void createArticle(@RequestBody Article article){
        articleService.addArticle(article);
    }

    //user make changes in their article

    @PreAuthorize("hasAnyAuthority('User')")
    @PutMapping("{id}")
    public void editArticle(@PathVariable UUID id, @RequestBody Article article){
        articleService.updateArticle(article, id);
    }

    //delete the article

    @DeleteMapping("{id}")
    public void deleteArticle(@PathVariable UUID id){
        articleService.deleteArticle(id);
    }

    //get particular article by that article id

    @GetMapping("{id}")
    public Article getArticleBySlug(@PathVariable UUID id){
        return articleService.getArticlesById(id);
    }

    //get
}
