package com.treshermanitos.api.application.repository;

import com.treshermanitos.api.domain.Product;
import com.treshermanitos.api.application.dto.StadisticsProducts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Page<Product> findAllByHasStockIsTrue(Pageable pageable, int categoryId);

    Optional<Product> findByIdAndHasStockIsTrue(Long id);

    List<StadisticsProducts> get5mostSales();

    Product save(Product product);
}
