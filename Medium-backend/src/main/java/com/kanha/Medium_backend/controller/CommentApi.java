package com.kanha.Medium_backend.controller;

import com.kanha.Medium_backend.Service.CommentService;
import com.kanha.Medium_backend.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/comments")
public class CommentApi {

    @Autowired
    private final CommentService commentService;

    public CommentApi(CommentService commentService) {
        this.commentService = commentService;
    }

    //adding the comments

    @PreAuthorize("hasAuthority('USERS')")
    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(
            @RequestParam UUID articleId,
            @RequestParam UUID userId,
            @RequestParam String content,
            @RequestParam(required = false) UUID parentCommentId
    ){
        return new ResponseEntity<>(commentService.addComment(articleId, userId, content, parentCommentId).getStatusCode());
    }

    //get all the comments

    @GetMapping("/article/{articleId}")
    public ResponseEntity<List<Comment>> getComments(@PathVariable UUID articleId){
        return commentService.getComments(articleId);
    }

    @PreAuthorize("hasAnyAuthority('USERS', 'ADMIN')")
    @DeleteMapping("/article/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable UUID id){
        return commentService.deleteComment(id);
    }
}
