package com.treshermanitos.api.purchase;

import com.treshermanitos.customer.Customer;
import com.treshermanitos.api.purchase.purchasesProducts.PurchaseProduct;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "`purchase`")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod payment;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    private String state;

    @Column(columnDefinition = "TIMESTAMP", name = "createdAt")
    private Date createdAt;
    @Column(columnDefinition = "TIMESTAMP", name = "updatedAt")
    private Date updatedAt;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.PERSIST)
    private List<PurchaseProduct> purchaseProducts;

    @PrePersist
    private void prePersist(){
        this.setState("pending");
        setCreatedAt(new Date());
        setUpdatedAt(getCreatedAt());
    }

    @PreUpdate
    protected void onUpdate() {
        setUpdatedAt(new Date());
    }
}
