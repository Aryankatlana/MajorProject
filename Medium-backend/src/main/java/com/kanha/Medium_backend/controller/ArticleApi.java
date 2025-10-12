package com.kanha.Medium_backend.controller;

import com.kanha.Medium_backend.Service.ArticleService;
import com.kanha.Medium_backend.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/article")
public class ArticleApi {

    @Autowired
    ArticleService articleService;

    //listing all the articles
    @GetMapping("/get")
    private List<Article> getAllArticle(){
        return articleService.getArticles();
    }

    //create the article
    @PostMapping("/add")
    private void addArticle(@RequestBody Article article){
        articleService.addArticle(article);
    }
    /*  update -> put
    * get by slug -> get
    *  list (filters: tag, author, feed) -> getAll
    *  delete -> delete
    *  publish -> post
    * */


}
