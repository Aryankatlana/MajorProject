package com.kanha.Medium_backend.Service;

import com.kanha.Medium_backend.Repository.ArticleRepo;
import com.kanha.Medium_backend.Repository.CommentRepo;
import com.kanha.Medium_backend.Repository.UserRepo;
import com.kanha.Medium_backend.model.Article;
import com.kanha.Medium_backend.model.Comment;
import com.kanha.Medium_backend.model.Role;
import com.kanha.Medium_backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    @Autowired
    AuthService authService;

    @Autowired
    ArticleRepo articleRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CommentRepo commentRepo;

    //adding the comment

    public ResponseEntity<Comment> addComment(UUID articleId, UUID userId, String content, UUID parentCommentId) {
        UUID currUserId = authService.providingUserId();

        if(userId.equals(currUserId)){
            throw new RuntimeException("You can't comment on yourself");
        }

        Article article = articleRepo.findById(articleId)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment commentObj = new Comment();

        //checking one condition that only diff user can comment to each other not same
            //then only we add comment
            commentObj.setArticle(article);
            commentObj.setUser(user);
            commentObj.setContent(content);

        if (parentCommentId != null) {
            Comment parent = commentRepo.findById(parentCommentId)
                    .orElseThrow(() -> new RuntimeException("Parent comment not found"));
            commentObj.setParentComment(parent);
        }

        return new ResponseEntity<>(commentRepo.save(commentObj), HttpStatus.OK);
    }

    //list all the specific user's comment

    public ResponseEntity<List<Comment>> getComments(UUID articleId) {
        try{
            List<Comment> list = commentRepo.findByArticleId(articleId); //giving specific comments
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //list all the comment
    public ResponseEntity<List<Comment>> getAllComments(){
        return new ResponseEntity<>(commentRepo.findAll(), HttpStatus.OK);
    }

    //delete the comment

    public ResponseEntity<?> deleteComment(UUID commentId){
        UUID currWebUserId = authService.providingUserId();

        Comment comment = commentRepo.findById(commentId)
                .orElseThrow(
                        () -> new RuntimeException("Comment doesn't exists")
                );

        UUID commentOwnerId = comment.getUser().getId();

        User currUser = userRepo.findById(currWebUserId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(commentOwnerId.equals(currWebUserId) || currUser.getRole() == Role.ADMIN){
            commentRepo.deleteById(commentId);
            return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Unauthorized delete", HttpStatus.FORBIDDEN);
        }
    }
}
