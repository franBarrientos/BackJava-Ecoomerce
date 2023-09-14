package com.treshermanitos.treshermanitos.customer;

import java.util.Date;
import java.util.List;

import com.treshermanitos.treshermanitos.purchase.Purchase;
import com.treshermanitos.treshermanitos.user.User;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Builder
@Entity
@Table(name = "`customer`")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer dni;

    private String addres;

    private String phone;

    @OneToOne
    @JoinColumn(name = "userId")
    private User user;

    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases;

    @Column(columnDefinition = "TIMESTAMP", name = "createdAt")
    private Date createdAt;
    @Column(columnDefinition = "TIMESTAMP", name = "updatedAt")
    private Date updatedAt;

    @PrePersist
    private void prePersist() {
        setCreatedAt(new Date());
        setUpdatedAt(getCreatedAt());
    }

    @PreUpdate
    protected void onUpdate() {
        setUpdatedAt(new Date());
    }


}
