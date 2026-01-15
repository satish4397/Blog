package com.example.blog.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@Data
@Document(collection = "posts")
public class PostEntity {

    @Id
    private String id;
    private String title;
    private String description;
}
