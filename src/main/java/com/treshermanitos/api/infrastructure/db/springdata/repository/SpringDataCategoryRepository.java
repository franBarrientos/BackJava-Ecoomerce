package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.application.dto.StadisticsCategory;
import com.treshermanitos.api.infrastructure.db.springdata.entities.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringDataCategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Page<CategoryEntity> findAllByStateIsTrue(Pageable pageable);

    Optional<CategoryEntity> findByIdAndStateIsTrue(Long id);

    @Query(value = "SELECT new com.treshermanitos.api.application.dto.StadisticsCategory( " +
            "c.id, c.name, SUM(pp.quantity)) FROM Category c " +
            "JOIN c.products p " +
            "JOIN p.purchaseProducts pp " +
            "GROUP BY p.id, p.name ORDER BY SUM(pp.quantity) DESC LIMIT 5")
    List<StadisticsCategory> get5mostSales();

}
