package com.example.blog.controller;

import com.example.blog.entity.PostEntity;
import com.example.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "https://blog-1-rs3h.onrender.com/")
@RestController
@RequestMapping("api/posts")
public class PostController {

    @Autowired(required = true)
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostEntity> createPost(@RequestBody PostEntity post) {
        PostEntity createdPost = postService.create(post);

        if (createdPost != null) {
            // Return 201 Created with the created entity
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(createdPost);
        } else {
            // Return 400 Bad Request or 500 depending on your logic
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<PostEntity> getAllPosts() {
        return postService.getAllPost();
    }

    @GetMapping("/{id}")
    public  PostEntity getSinglePost(@PathVariable String id) {
        return postService.getSinglePost(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        boolean deleted = postService.delete(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }


    @PutMapping("/{id}")
    public PostEntity updatePost(@PathVariable String id, @RequestBody PostEntity post) {
        return postService.update(id,post);
    }
}
