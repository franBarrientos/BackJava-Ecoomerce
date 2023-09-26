package com.treshermanitos.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import java.util.Set;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category {
    private Long id;

    private String name;

    private String img;

    private Date createdAt;

    private Date updatedAt;

    private Boolean state;

    private Set<Product> products;


    public Long getId() {
        return id;
    }

    public void setId(Long p_id) {
        id = p_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String p_name) {
        name = p_name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String p_img) {
        img = p_img;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date p_createdAt) {
        createdAt = p_createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date p_updatedAt) {
        updatedAt = p_updatedAt;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean p_state) {
        state = p_state;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> p_products) {
        products = p_products;
    }
}
