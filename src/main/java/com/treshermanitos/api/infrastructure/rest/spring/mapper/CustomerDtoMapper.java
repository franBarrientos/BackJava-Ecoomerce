package com.treshermanitos.api.infrastructure.rest.spring.mapper;

import com.treshermanitos.api.domain.Customer;
import com.treshermanitos.api.infrastructure.rest.spring.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerDtoMapper {

    CustomerDTO toDto(Customer customer);

    Customer toDomain(CustomerDTO customerDTO);
}
