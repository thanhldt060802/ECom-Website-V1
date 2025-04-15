package com.thanhldt060802.ecom.service;

import com.thanhldt060802.ecom.model.Product;
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

    public boolean isIdExisted(Long id) {
        return this.productRepository.existsById(id);
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Product getById(Long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id!"));
    }

    public List<Product> getAllByCategoryId(Long categoryId) {
        return this.productRepository.findAllByCategoryId(categoryId);
    }

}
