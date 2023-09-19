package com.treshermanitos.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Product {
    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Category category;

    private String img;

    private Long stock;

    private Boolean hasStock;

    private Boolean fav;

    private Date createdAt;

    private Date updatedAt;

    private List<PurchaseProduct> purchaseProducts;

}
