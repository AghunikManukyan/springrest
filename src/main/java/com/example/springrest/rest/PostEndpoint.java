package com.example.springrest.rest;

import com.example.springrest.model.Post;
import com.example.springrest.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PostEndpoint {

    @Autowired
    private PostRepository postRepository;


    @PostMapping("/post/add")
    public ResponseEntity add(@RequestBody Post post) {
        postRepository.save(post);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/post/update")
    public ResponseEntity update(@RequestBody Post post) {
        if (postRepository.findById(post.getId()).isPresent()) {
            postRepository.save(post);
            return ResponseEntity
                    .ok(post);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/posts")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(postRepository.findAll());
    }


    @GetMapping("/posts/{id}")
    public ResponseEntity getById(@PathVariable("id") int id) {
        Optional<Post> byId = postRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/posts/{id}")
    public ResponseEntity deleteById(@PathVariable("id") int id) {
        Optional<Post> byId = postRepository.findById(id);
        if (byId.isPresent()) {
            postRepository.deleteById(id);
            return ResponseEntity
                    .ok()
                    .build();
        }
        return ResponseEntity.notFound().build();
    }
}
