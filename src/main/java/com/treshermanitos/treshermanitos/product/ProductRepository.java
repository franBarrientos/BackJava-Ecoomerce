package com.treshermanitos.treshermanitos.product;

import com.treshermanitos.treshermanitos.purchase.projections.ProductProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> getAllByHasStockIsTrue(Pageable pageable);

    Optional<Product> getByIdAndHasStockIsTrue(Long id);
    
}
