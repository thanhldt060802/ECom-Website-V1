package com.thanhldt060802.ecom.controller;

import com.thanhldt060802.ecom.model.Category;
import com.thanhldt060802.ecom.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("ecom_website_v1/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(this.categoryService.findAllCategories());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(this.categoryService.findCategoryById(id));
    }

    @PostMapping("/")
    public ResponseEntity<String> createCategory(@RequestBody Category newCategory) {
        this.categoryService.createCategory(newCategory);
        return ResponseEntity.ok("Create category success!");
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id, @RequestBody Category updatingCategory) {
        this.categoryService.updateCategory(id, updatingCategory);
        return ResponseEntity.ok("Update category success!");
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long id) {
        this.categoryService.deleteCategoryById(id);
        return ResponseEntity.ok("Delete category success!");
    }

}
