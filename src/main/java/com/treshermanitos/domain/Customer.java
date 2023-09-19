package com.treshermanitos.domain;

import java.util.Date;
import java.util.List;

public class Customer {
    private Long id;

    private Integer dni;

    private String addres;

    private String phone;

    private User user;

    private List<Purchase> purchases;

    private Date createdAt;
    private Date updatedAt;

    public Customer(Long id, Integer dni, String addres, String phone, User user, List<Purchase> purchases, Date createdAt, Date updatedAt) {
        this.id = id;
        this.dni = dni;
        this.addres = addres;
        this.phone = phone;
        this.user = user;
        this.purchases = purchases;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long p_id) {
        id = p_id;
    }

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer p_dni) {
        dni = p_dni;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String p_addres) {
        addres = p_addres;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String p_phone) {
        phone = p_phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User p_user) {
        user = p_user;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> p_purchases) {
        purchases = p_purchases;
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
}
