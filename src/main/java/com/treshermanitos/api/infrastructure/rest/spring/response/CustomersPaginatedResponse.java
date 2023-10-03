package com.treshermanitos.api.infrastructure.rest.spring.response;

import java.util.List;

import com.treshermanitos.api.application.dto.CustomerDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class CustomersPaginatedResponse extends PaginatedResponseBase {
    private List<CustomerDTO> customers;
}
