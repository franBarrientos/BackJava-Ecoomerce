package com.treshermanitos.api.application.repository;

import com.treshermanitos.api.domain.Category;
import com.treshermanitos.api.application.dto.StadisticsCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    Page<Category> findAllByStateIsTrue(Pageable pageable);
    Optional<Category> findByIdAndStateIsTrue(Long id);
    Category save(Category category);

    List<StadisticsCategory> get5mostSales();

    boolean deleteById(Long id);
}
