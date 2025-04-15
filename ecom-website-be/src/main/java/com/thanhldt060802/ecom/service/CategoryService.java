package com.thanhldt060802.ecom.service;

import com.thanhldt060802.ecom.model.Category;
import com.thanhldt060802.ecom.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public boolean isIdExisted(Long id) {
        return this.categoryRepository.existsById(id);
    }

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public Category getById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid id!"));
    }

}
