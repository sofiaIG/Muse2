package com.codeclan.example.Muse.controllers;

import com.codeclan.example.Muse.dtos.CommentDTO;
import com.codeclan.example.Muse.dtos.CreatePostDTO;
import com.codeclan.example.Muse.models.Comment;
import com.codeclan.example.Muse.models.Post;
import com.codeclan.example.Muse.models.User;
import com.codeclan.example.Muse.repositories.CommentRepository;
import com.codeclan.example.Muse.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostRepository postRepository;


    @GetMapping(value = "/posts")
    public ResponseEntity<List<Post>> getAllPosts(){
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }


    @PostMapping(value = "/post-create")
    public ResponseEntity<Post> postPost(@RequestBody CreatePostDTO createPostDTO){
        String title = createPostDTO.getTitle();
        String text = createPostDTO.getText();
        Long userId = createPostDTO.getUserId();
        User user = new User();
        user.setId(userId);
        Post post = new Post(title, text, user);
        postRepository.save(post);
        return new ResponseEntity(post, HttpStatus.CREATED);
    }

    @GetMapping(value = "/post/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id){
        Long idLong = Long.parseLong(id);
        return new ResponseEntity(postRepository.findById(idLong), HttpStatus.OK);
    }



//    @DeleteMapping(value = "/deletePost/{id}")
//    public void deletePost(@PathVariable Long id){
//        postRepository.deleteById(id);
//    }




}
