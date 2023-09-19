package com.treshermanitos.api.domain;

import java.util.Date;
import java.util.Set;

public class Category {
    private Long id;

    private String name;

    private String img;

    private Date createdAt;

    private Date updatedAt;

    private Boolean state;

    private Set<Product> products;

}
