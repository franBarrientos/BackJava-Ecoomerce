package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.application.dto.StadisticsProducts;
import com.treshermanitos.api.infrastructure.db.springdata.entities.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringDataProductRepository extends JpaRepository<ProductEntity, Long> {

    Page<ProductEntity> findByHasStockIsTrueAndCategoryId(Pageable pageable, int categoryId);

    Optional<ProductEntity> findByIdAndHasStockIsTrue(Long id);

    @Query(value = "SELECT new com.treshermanitos.api.application.dto.StadisticsProducts( " +
            "p.id, p.name, SUM(pp.quantity)) FROM Product p JOIN p.purchaseProducts pp " +
            "GROUP BY p.id ORDER BY SUM(pp.quantity) DESC LIMIT 5")
    List<StadisticsProducts> get5ProductsMostSales();

}
