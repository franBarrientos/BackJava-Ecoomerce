package com.treshermanitos.api.infrastructure.db.springdata.entities;

import com.treshermanitos.api.domain.PaymentMethod;
import com.treshermanitos.api.domain.PurchaseProduct;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Purchase")
@Table(name = "`purchase`")
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Enumerated(EnumType.STRING)
    private PaymentMethod payment;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private CustomerEntity customer;

    private String state;

    private Date createdAt;

    private Date updatedAt;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.EAGER)
    private List<PurchaseProductEntity> purchaseProducts;

    @PrePersist
    private void prePersist() {
        this.setState("pendiente");
        this.setCreatedAt(new Date());
        this.setUpdatedAt(getCreatedAt());
    }

    @PreUpdate
    protected void onUpdate() {
        this.setUpdatedAt(new Date());
    }

}
