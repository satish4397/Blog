package com.example.blog.service;

import com.example.blog.entity.PostEntity;
import com.example.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public PostEntity create(PostEntity post) {
        return postRepository.save(post);
    }

    public List<PostEntity> getAllPost() {
        return postRepository.findAll();
    }

    public PostEntity getSinglePost(String id) {
        return postRepository.findById(id).orElse(null);
    }

    public boolean delete(String id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }



    public PostEntity update(String id, PostEntity newPost){
        PostEntity oldPost = postRepository.findById(id).orElse(null);

        if(oldPost != null){
            oldPost.setTitle(newPost.getTitle() != null && !newPost.getTitle().isEmpty() ? newPost.getTitle() : oldPost.getTitle());
            oldPost.setDescription(newPost.getDescription() != null && newPost.getDescription().isEmpty() ? newPost.getDescription() : oldPost.getDescription());
            newPost.setId(id);
            postRepository.save(newPost);
        }
        return newPost;
    }
}
