package com.treshermanitos.treshermanitos.customer;

import java.util.List;

import com.treshermanitos.treshermanitos.config.PaginatedResponseBase;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class CustomersPaginatedResponse extends PaginatedResponseBase{
    private List<Customer> customers;
}
