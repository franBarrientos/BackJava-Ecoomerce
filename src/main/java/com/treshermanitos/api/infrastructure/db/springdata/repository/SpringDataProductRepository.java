package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.infrastructure.db.springdata.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataProductRepository extends JpaRepository<ProductEntity, Long> {

    Page<ProductEntity> findAllByHasStockIsTrue(Pageable pageable);

    Optional<ProductEntity> findByIdAndHasStockIsTrue(Long id);


}
