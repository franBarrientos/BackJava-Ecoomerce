package com.treshermanitos.treshermanitos.customer;

import java.util.List;

import com.treshermanitos.treshermanitos.config.PaginatedResponseBase;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class CustomersPaginatedResponse extends PaginatedResponseBase{
    private List<CustomerDTO> customers;
}
