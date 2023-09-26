package com.treshermanitos.api.application.service;

import com.treshermanitos.api.application.exceptions.NotFoundException;
import com.treshermanitos.api.application.repository.CategoryRepository;
import com.treshermanitos.api.domain.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<Category> getAll(Pageable pageable){
        return this.categoryRepository.findAllByStateIsTrue(pageable);
    }

    public Category getById(Long id){
        return this.categoryRepository.findByIdAndStateIsTrue(id)
                .orElseThrow(()->new NotFoundException("category "+ id+" not found"));
    }

    public Category createOne(Category category){
        return this.categoryRepository.save(category);
    }
    public Category updateById(Long id, Category category){
        Category categoryToUpdate = this.categoryRepository.findByIdAndStateIsTrue(id)
                .orElseThrow(() -> new NotFoundException("category " + id + " not found "));
        if (category.getImg() != null) {
            categoryToUpdate.setImg(category.getImg());
        }


        if (category.getName() != null) {
            categoryToUpdate.setName(category.getName());
        }

        return this.categoryRepository.save(categoryToUpdate);
    }

    public void deleteById(Long id){
        Category category = this.categoryRepository.findByIdAndStateIsTrue(id)
                .orElseThrow(() -> new NotFoundException("category " + id + " not found "));
        category.setState(false);
        this.categoryRepository.save(category);
    }

}
