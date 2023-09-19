package com.treshermanitos.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findAllByStateIsTrue(Pageable pageable);
    Optional<Category> findByIdAndStateIsTrue(Long id);

}
