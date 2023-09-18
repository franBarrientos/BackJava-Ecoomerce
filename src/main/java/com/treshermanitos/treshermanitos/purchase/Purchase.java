package com.treshermanitos.treshermanitos.purchase;

import com.treshermanitos.treshermanitos.customer.Customer;
import com.treshermanitos.treshermanitos.product.Product;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "purchases_products",
            joinColumns = @JoinColumn(
                    name = "purchaseId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "productId", referencedColumnName = "id"))
    private List<Product> products;

}
