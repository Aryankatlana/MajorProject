package com.kanha.Medium_backend.Service;

import com.kanha.Medium_backend.Repository.ArticleRepo;
import com.kanha.Medium_backend.Repository.UserRepo;
import com.kanha.Medium_backend.model.Article;
import com.kanha.Medium_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ArticleService {

    @Autowired
    ArticleRepo articleRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthService authService;

    // add article

    public void addArticle(Article article) {

        //get the user id -> jisme ham article store karna chahte h
        UUID id = authService.providingUserId();

        //ab us id se ham poora User find karte h
        User user = userRepo.findById(id).get(); // .get() -> try karo Optional ke andar jo object h use return karo

        //then us user ko article me store karte hai or ye map ho jata h Many To One ke form me
        article.setUser(user);
        articleRepo.save(article);
    }

    //get article of Specific user By Id

    public List<Article> getArticles() {
        System.out.println("All article got successsfully");
        return articleRepo.findAll();
    }

    //update the article

    public void updateArticle(Article article, UUID id) {
        Article article1 = articleRepo.getReferenceById(id);

        if(article1 != null) {
            article1.setUser(article1.getUser());
            article1.setTitle(article.getTitle());
            article1.setContent(article.getContent());
            article1.setTags(article.getTags());
        }
        try {
            articleRepo.save(article);
            System.out.println("Successfully updated");
//            return new ResponseEntity<>("Successfully Updated the Article", HttpStatus.OK);
        } catch (Exception e) {
//            return new ResponseEntity<>("Failed to save this article", HttpStatus.BAD_REQUEST);
            System.out.println("Failed to save the article");
        }
    }

    public void deleteArticle(UUID id) {
        articleRepo.deleteById(id);
    }

    public Article getArticlesById(UUID id) {
        return articleRepo.getReferenceById(id);
    }
}
