package com.example.blog.repository;

import com.example.blog.entity.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<PostEntity, String> {
}
