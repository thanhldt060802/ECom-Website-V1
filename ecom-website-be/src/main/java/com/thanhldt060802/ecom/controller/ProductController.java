package com.thanhldt060802.ecom.controller;

import com.thanhldt060802.ecom.model.Product;
import com.thanhldt060802.ecom.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("ecom_website_v1/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(this.productService.findAllProducts());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(this.productService.findProductById(id));
    }

    @GetMapping("/category-id/{categoryId}")
    public ResponseEntity<List<Product>> getAllProductsByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(this.productService.findAllProductsByCategoryId(categoryId));
    }

    @PostMapping("/")
    public ResponseEntity<String> createProduct(@RequestBody Product newProduct) {
        this.productService.createProduct(newProduct);
        return ResponseEntity.ok("Create product success!");
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updatingProduct) {
        this.productService.updateProduct(id, updatingProduct);
        return ResponseEntity.ok("Update product success!");
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id) {
        this.productService.deleteProductById(id);
        return ResponseEntity.ok("Delete product success!");
    }

}
