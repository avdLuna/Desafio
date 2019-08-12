package com.example.desafio.service;

import com.example.desafio.model.Hashtag;
import com.example.desafio.model.Post;
import com.example.desafio.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PostService {

    @Autowired
    PostRepository repository;

    public List<Hashtag> getTopHashtags(String string){
        List<Post> posts = getFilteredPosts(string);

        return getTopOrderedHashtags(posts);
    }

    private List<Post> getFilteredPosts(String string) {
        Iterator<Post> iterator = this.repository.findAll().iterator();
        List<Post> posts = new ArrayList<Post>();
        while(iterator.hasNext()){
            posts.add(iterator.next());
        }
        posts.removeIf(p -> (p.getContent() == null || !isContain(p.getContent(), string) || p.getHashtags().isEmpty()));
        return posts;
    }

    private boolean isContain(String source, String subItem){
        String pattern = "\\b" + subItem.toLowerCase() + "\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(source.toLowerCase());
        return m.find();
    }

    private List<String> getHashtagsNames(List<Post> posts) {
        List<String> hashtags = new ArrayList<String>();
        posts.forEach((p) -> p.getHashtags().forEach(h -> hashtags.add(h)));
        return hashtags;
    }

    private List<Hashtag> orderHashtags(List<String> hashtags) {
        Set<Hashtag> postsSet = new LinkedHashSet<Hashtag>();

        hashtags.forEach(h -> postsSet.add(new Hashtag(h, Collections.frequency(hashtags,h))));

        List<Hashtag> orderedHashtags = new ArrayList<Hashtag>(postsSet);
        Collections.sort(orderedHashtags);
        return orderedHashtags;
    }

    private List<Hashtag> getTopOrderedHashtags (List<Post> posts){
        List<String> hashtags = getHashtagsNames(posts);

        List<Hashtag> orderedHashtags = orderHashtags(hashtags);

        if(orderedHashtags.size() >= 10){
            return orderedHashtags.subList(0,10);
        } else {
            return orderedHashtags;
        }
    }

}
