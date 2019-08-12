package com.example.desafio.controller;

import com.example.desafio.model.Hashtag;
import com.example.desafio.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class PostController {

    @Autowired
    PostService service;

    @GetMapping("/posts/search/{string}")
    public ResponseEntity<List<Hashtag>> getTopHashtags(@PathVariable String string){
        List<Hashtag> topHashtags = this.service.getTopHashtags(string);
        return new ResponseEntity<>(topHashtags, HttpStatus.OK);
    }
}
