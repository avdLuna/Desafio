package com.example.desafio.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;
import java.util.List;

@Document(indexName = "posts", type = "docs")
public class Post {

    @Id
    private String id;
    private String service;
    private String content;
    private List<String> hashtagsNames;

    public Post() {
        this.hashtagsNames = new ArrayList<String>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getHashtags() {
        return hashtagsNames;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtagsNames = hashtags;
    }
}
