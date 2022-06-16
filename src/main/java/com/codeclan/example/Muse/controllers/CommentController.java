package com.codeclan.example.Muse.controllers;

import com.codeclan.example.Muse.dtos.CommentDTO;
import com.codeclan.example.Muse.models.Comment;
import com.codeclan.example.Muse.models.Post;
import com.codeclan.example.Muse.models.User;
import com.codeclan.example.Muse.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentRepository commentRepository;

    @GetMapping(value = "/comments")
    public ResponseEntity<List<Comment>> getAllComments(){
        return new ResponseEntity<>(commentRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<List<Comment>> getAllCommentsByPostId(@PathVariable Long id){
        return new ResponseEntity<>(commentRepository.findAllByPostId(id), HttpStatus.OK);
    }

    @PostMapping(value = "/comment")
    public ResponseEntity<Comment> postComment(@RequestBody CommentDTO commentDTO){
        String content = commentDTO.getContent();
        Long userId = commentDTO.getUser_id();
        Long postId = commentDTO.getPost_id();
        User user = new User();
        user.setId(userId);
        Post post = new Post();
        post.setId(postId);
        Comment comment = new Comment(content, post, user);
        commentRepository.save(comment);
        return new ResponseEntity(post, HttpStatus.CREATED);
    }








}
