package com.example.blog.controller;

import com.example.blog.entity.PostEntity;
import com.example.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostEntity> getAllPosts() {
        return postService.getAll();
    }

    @PostMapping
public ResponseEntity<PostEntity> createPost(@RequestBody PostEntity post) {
    PostEntity createdPost = postService.create(post);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
}

@PutMapping("/{id}")
public ResponseEntity<PostEntity> updatePost(@PathVariable String id, @RequestBody PostEntity post) {
    PostEntity updatedPost = postService.update(id, post);
    return ResponseEntity.ok(updatedPost);
}


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
