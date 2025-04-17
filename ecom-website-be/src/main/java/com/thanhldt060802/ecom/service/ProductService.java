package com.thanhldt060802.ecom.service;

import com.thanhldt060802.ecom.model.Category;
import com.thanhldt060802.ecom.model.Product;
import com.thanhldt060802.ecom.repository.CategoryRepository;
import com.thanhldt060802.ecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryService categoryService;

    public boolean isProductExistedById(Long id) {
        return this.productRepository.existsById(id);
    }

    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    public Product findProductById(Long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of product is not valid!"));
    }

    public List<Product> findAllProductsByCategoryId(Long categoryId) {
        return this.productRepository.findAllByCategoryId(categoryId);
    }

    public void createProduct(Product newProduct) {
        Category foundCategory = this.categoryService.findCategoryById(newProduct.getCategory().getCategoryId());
        newProduct.setCategory(foundCategory);

        this.productRepository.save(newProduct);
    }

    public void updateProduct(Long id, Product updatingProduct) {
        Product foundProduct = this.productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of product is not valid!"));
        Category foundCategory = this.categoryService.findCategoryById(updatingProduct.getCategory().getCategoryId());
        foundProduct.setCategory(foundCategory);
        foundProduct.setName(updatingProduct.getName());
        foundProduct.setDescription(updatingProduct.getDescription());
        foundProduct.setPrice(updatingProduct.getPrice());
        foundProduct.setDiscountPercentage(updatingProduct.getDiscountPercentage());
        foundProduct.setStock(updatingProduct.getStock());
        foundProduct.setImageUrl(updatingProduct.getImageUrl());

        this.productRepository.save(foundProduct);
    }

    public void deleteProductById(Long id) {
        if(!this.isProductExistedById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of product is not valid!");
        }

        this.productRepository.deleteById(id);
    }

}
