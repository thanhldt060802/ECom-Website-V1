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

    public boolean isCategoryExistedById(Long id) {
        return this.categoryRepository.existsById(id);
    }

    public List<Category> findAllCategories() {
        return this.categoryRepository.findAll();
    }

    public Category findCategoryById(Long id) {
        return this.categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of category is not valid!"));
    }

    public void createCategory(Category newCategory) {
        this.categoryRepository.save(newCategory);
    }

    public void updateCategory(Long id, Category updatingCategory) {
        Category foundCategory = this.categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of category is not valid!"));
        foundCategory.setName(updatingCategory.getName());
        foundCategory.setDescription(updatingCategory.getDescription());

        this.categoryRepository.save(foundCategory);
    }

    public void deleteCategoryById(Long id) {
        if (!this.isCategoryExistedById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Id of category is not valid!");
        }

        this.categoryRepository.deleteById(id);
    }

}
