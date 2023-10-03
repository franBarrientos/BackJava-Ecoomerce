package com.treshermanitos.api.application.service;

import com.treshermanitos.api.application.dto.CategoryDTO;
import com.treshermanitos.api.application.exceptions.NotFoundException;
import com.treshermanitos.api.application.mapper.CategoryDtoMapper;
import com.treshermanitos.api.application.repository.CategoryRepository;
import com.treshermanitos.api.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryDtoMapper categoryDtoMapper;


    public Page<CategoryDTO> getAll(Pageable pageable){
        return this.categoryRepository.findAllByStateIsTrue(pageable)
                .map(this.categoryDtoMapper::toDto);
    }

    public CategoryDTO getById(Long id){
        return this.categoryDtoMapper.toDto
                (this.categoryRepository.findByIdAndStateIsTrue(id)
                .orElseThrow(()->new NotFoundException("category "+ id+" not found")));
    }

    public CategoryDTO createOne(CategoryDTO category){
        return this.categoryDtoMapper.toDto
                (this.categoryRepository.save
                        (this.categoryDtoMapper.toDomain(category)));
    }

    public CategoryDTO updateById(Long id, CategoryDTO category){
        Category categoryToUpdate = this.categoryRepository.findByIdAndStateIsTrue(id)
                .orElseThrow(() -> new NotFoundException("category " + id + " not found "));
        if (category.getImg() != null) {
            categoryToUpdate.setImg(category.getImg());
        }


        if (category.getName() != null) {
            categoryToUpdate.setName(category.getName());
        }

        return this.categoryDtoMapper.toDto
                (this.categoryRepository.save(categoryToUpdate));
    }

    public String deleteById(Long id){
        Category category = this.categoryRepository.findByIdAndStateIsTrue(id)
                .orElseThrow(() -> new NotFoundException("category " + id + " not found "));
        category.setState(false);
        this.categoryRepository.save(category);
        return "Category " + id + " deleted";
    }

}
