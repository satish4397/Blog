package com.example.blog.service;

import com.example.blog.entity.PostEntity;
import com.example.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostEntity> getAll() {
        return postRepository.findAll();
    }

    public PostEntity create(PostEntity post) {
        return postRepository.save(post);
    }

    public PostEntity update(String id, PostEntity post) {
        return postRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(post.getTitle());
                    existing.setDescription(post.getDescription());
                    return postRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public void delete(String id) {
        postRepository.deleteById(id);
    }
}
