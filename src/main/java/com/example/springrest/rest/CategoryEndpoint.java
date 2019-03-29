package com.example.springrest.rest;

import com.example.springrest.model.Category;
import com.example.springrest.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CategoryEndpoint {


    @Autowired
    private CategoryRepository categoryRepository;


    @PostMapping("/category/add")
    public ResponseEntity add(@RequestBody Category category) {
        categoryRepository.save(category);
        return ResponseEntity.ok(category);
    }

    @PutMapping("/category/update")
    public ResponseEntity update(@RequestBody Category category) {
        if (categoryRepository.findById(category.getId()).isPresent()) {
            categoryRepository.save(category);
            return ResponseEntity
                    .ok(category);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/categories")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(categoryRepository.findAll());
    }


    @GetMapping("/categoriss/{id}")
    public ResponseEntity getById(@PathVariable("id") int id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/categories/{id}")
    public ResponseEntity deleteById(@PathVariable("id") int id) {
        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isPresent()) {
            categoryRepository.deleteById(id);
            return ResponseEntity
                    .ok()
                    .build();
        }
        return ResponseEntity.notFound().build();
    }
}
