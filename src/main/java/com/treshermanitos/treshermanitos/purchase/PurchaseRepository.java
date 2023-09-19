package com.treshermanitos.treshermanitos.purchase;

import com.treshermanitos.treshermanitos.purchase.dto.ProductSold;
import com.treshermanitos.treshermanitos.purchase.dto.PurchaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query("SELECT NEW com.treshermanitos.treshermanitos.purchase.dto.PurchaseDTO(" +
            "p.id, p.payment, p.customer.id, p.customer.dni, p.customer.addres," +
            "p.customer.phone, p.customer.user.firstName, p.customer.user.lastName, p.state, p.createdAt) " +
            "FROM Purchase p")
    Page<PurchaseDTO> findAllPurchasesAsDTOs(Pageable pageable);
    @Query("SELECT NEW com.treshermanitos.treshermanitos.purchase.dto.PurchaseDTO(" +
            "p.id, p.payment, p.customer.id, p.customer.dni, p.customer.addres," +
            "p.customer.phone, p.customer.user.firstName, p.customer.user.lastName, p.state, p.createdAt) " +
            "FROM Purchase p " +
            "WHERE p.customer.user.firstName LIKE %:name% "+
            "OR p.customer.user.lastName LIKE %:name% " +
            "OR p.customer.dni = :dni")
    Page<PurchaseDTO> findPurchasesAsDTOsByCustomerDetails(Pageable pageable, String name, Integer dni);

    @Query("SELECT NEW com.treshermanitos.treshermanitos.purchase.dto.PurchaseDTO(" +
            "p.id, p.payment, p.customer.id, p.customer.dni, p.customer.addres," +
            "p.customer.phone, p.customer.user.firstName, p.customer.user.lastName, p.state, p.createdAt) " +
            "FROM Purchase p " +
            "WHERE p.id = :purchaseId")
    Optional<PurchaseDTO> findPurchaseAsDTOById(Long purchaseId);


    @Query("SELECT NEW com.treshermanitos.treshermanitos.purchase.dto.ProductSold( " +
            "pp.id, pp.name, p.quantity, p.totalPrice, pp.price) " +
            "FROM PurchaseProduct p JOIN  p.product pp " +
            "WHERE p.purchase.id = :purchaseId")
    List<ProductSold> findProductsAsProjectionsByPurchaseId(Long purchaseId);

}
