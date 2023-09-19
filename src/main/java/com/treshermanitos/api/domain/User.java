package com.treshermanitos.api.domain;

import java.util.Date;
import java.util.Set;

public class User {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String city;

    private Integer age;

    private Set<Role> roles;

    private String province;

    private Boolean state;

    private Date createdAt;
    private Date updatedAt;
    private Customer customer;

    public User(Long id, String firstName, String lastName, String email, String password, String city, Integer age, Set<Role> roles, String province, Boolean state, Date createdAt, Date updatedAt, Customer customer) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.city = city;
        this.age = age;
        this.roles = roles;
        this.province = province;
        this.state = state;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long p_id) {
        id = p_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String p_firstName) {
        firstName = p_firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String p_lastName) {
        lastName = p_lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String p_email) {
        email = p_email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String p_password) {
        password = p_password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String p_city) {
        city = p_city;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer p_age) {
        age = p_age;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> p_roles) {
        roles = p_roles;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String p_province) {
        province = p_province;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean p_state) {
        state = p_state;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer p_customer) {
        customer = p_customer;
    }
}
