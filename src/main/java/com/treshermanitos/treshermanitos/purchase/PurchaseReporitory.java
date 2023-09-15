package com.treshermanitos.treshermanitos.purchase;

import com.treshermanitos.treshermanitos.purchase.PurchaseProjection.ProductProjection;
import com.treshermanitos.treshermanitos.purchase.PurchaseProjection.PurchaseProjection;
import com.treshermanitos.treshermanitos.purchase.projections.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PurchaseReporitory extends JpaRepository<Purchase, Long> {


    @Query("SELECT p.id as id, p.payment AS payment, p.customer.id AS customerId, " +
            "p.customer.dni AS dni, p.customer.addres AS addres, p.customer.user.id AS userId, " +
            "p.customer.user.firstName AS firstName, p.customer.user.lastName AS lastName " +
            "FROM Purchase p ")
    Page<PurchaseProjectionClassI> findAllDefinitive(Pageable pageable);










    //NO ME TRAE VARIOS REGISTROS PERO ES INEFICIENTE, PORQUE
    // TRAE TODAS LAS ENTIDADES, Y LUEGO MAPEA
    Page<PurchaseProjection> findAllBy(Pageable pageable);






         // ESTO FUNCIONAAAA!!!

    @Query(value = "SELECT p.* " +
            "FROM purchases_products pp " +
            "JOIN product p ON pp.productId = p.id " +
            "WHERE pp.purchaseId = :purchaseId", nativeQuery = true)
    List<ProductProjection> findProductsByPurchaseId(@Param("purchaseId") Long purchaseId);


    //ESTO ES UNA MIERDA
/*  @Query("SELECT products FROM Purchase pp RIGHT JOIN pp.products products  WHERE pp.id = :purchaseId")
    List<ProductProjection> findProductsByPurchaseId(@Param("purchaseId") Long purchaseId);*/





    @Query("SELECT p.id as id, p.payment as payment, p.customer.id AS customerId, " +
            "p.customer.dni AS dni, p.customer.addres AS addres, p.customer.user.id AS userId, " +
            "p.customer.user.firstName AS firstName, p.customer.user.lastName AS lastName, " +
            "p.products AS products FROM Purchase p ")
    Page<PurchaseProjectionFaster> findAllByButRepeat(Pageable pageable);
    //ESTO ME TRAE VARIOS REGISTROS POR CADA PURCHASE




    @Query(value = "SELECT " +
            "p.id AS id, " +
            "p.payment AS payment, " +
            "customer.id AS customerId, " +
            "customer.dni AS dni, " +
            "customer.addres AS addres, " +
            "u.id AS userId, " +
            "u.firstName AS firstName, " +
            "u.lastName AS lastName, " +
            "GROUP_CONCAT(CONCAT(products.id, ':', products.name) SEPARATOR ', ') AS products " +
            "FROM Purchase p " +
            "JOIN Customer customer ON p.customerId = customer.id " +
            "JOIN User u ON customer.userId = u.id " +
            "LEFT JOIN Purchases_Products pp ON p.id = pp.purchaseId " +
            "LEFT JOIN Product products ON pp.productId = products.id " +
            "GROUP BY p.id",
            countQuery = " SELECT count(*) FROM Purchase",
            nativeQuery = true)
    Page<PurchaseProjectionFasterString> findAllByClosedProjectionFaster(Pageable pageable);

}