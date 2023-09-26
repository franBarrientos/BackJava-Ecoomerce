package com.treshermanitos.api.infrastructure.db.springdata.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.treshermanitos.api.infrastructure.db.springdata.entities.CategoryEntity;
/*
import com.treshermanitos.api.purchase.purchasesProducts.PurchaseProduct;
*/
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "Product")
@Table(name = "`product`")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    @ManyToOne()
    @JsonIgnoreProperties({"products"})
    @JoinColumn(name = "categoryId")
    private CategoryEntity category;

    private String img;

    private Long stock;

    private Boolean hasStock;

    private Boolean fav;

    private Date createdAt;

    private Date updatedAt;

/*
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private List<PurchaseProduct> purchaseProducts;
*/


    @PrePersist
    private void prePersist() {
        if (this.getStock() == null) {
            this.setStock(1l);
        }
        setCreatedAt(new Date());
        setUpdatedAt(getCreatedAt());
    }

    @PreUpdate
    protected void onUpdate() {
        setUpdatedAt(new Date());
    }


}
