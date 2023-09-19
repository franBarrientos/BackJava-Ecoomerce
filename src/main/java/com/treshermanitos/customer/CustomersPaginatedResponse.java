package com.treshermanitos.customer;

import java.util.List;

import com.treshermanitos.config.PaginatedResponseBase;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class CustomersPaginatedResponse extends PaginatedResponseBase {
    private List<CustomerDTO> customers;
}
