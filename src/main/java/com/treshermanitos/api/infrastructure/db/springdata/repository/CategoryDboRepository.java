package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.application.repository.CategoryRepository;
import com.treshermanitos.api.domain.Category;
import com.treshermanitos.api.application.dto.StadisticsCategory;
import com.treshermanitos.api.infrastructure.db.springdata.entities.CategoryEntity;
import com.treshermanitos.api.infrastructure.db.springdata.mapper.CategoryEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryDboRepository implements CategoryRepository {

    private final SpringDataCategoryRepository categoryRepository;
    private final CategoryEntityMapper categoryEntityMapper;

    @Override
    public Page<Category> findAllByStateIsTrue(Pageable pageable) {
        return this.categoryRepository.findAllByStateIsTrue(pageable)
                .map(categoryEntityMapper::toDomain);
    }

    @Override
    public Optional<Category> findByIdAndStateIsTrue(Long id) {
        Optional<CategoryEntity> category = this.categoryRepository.findByIdAndStateIsTrue(id);
        return category.isPresent()?
               Optional.of(this.categoryEntityMapper.toDomain(category.get()))
                :
                Optional.empty();
    }

    @Override
    public Category save(Category category) {
        return this.categoryEntityMapper.toDomain(
                this.categoryRepository.save(this.categoryEntityMapper.toEntity(category)));
    }

    @Override
    public List<StadisticsCategory> get5mostSales() {
        return this.categoryRepository.get5mostSales();
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<CategoryEntity> categoryEntity = this.categoryRepository.findByIdAndStateIsTrue(id);
        if (categoryEntity.isPresent()){
            Category category = this.categoryEntityMapper.toDomain(categoryEntity.get());
            category.setState(false);
            this.categoryRepository.save(this.categoryEntityMapper.toEntity(category));
            return true;
        }else {
            return false;
        }
    }
}
