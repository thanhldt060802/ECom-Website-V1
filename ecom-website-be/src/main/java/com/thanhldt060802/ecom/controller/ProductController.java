package com.thanhldt060802.ecom.controller;

import com.thanhldt060802.ecom.model.Product;
import com.thanhldt060802.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("ecom_v1/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(this.productService.getAll());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(this.productService.getById(id));
    }

    @GetMapping("/category-id/{categoryId}")
    public ResponseEntity<List<Product>> getAllProductsByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(this.productService.getAllByCategoryId(categoryId));
    }

}
