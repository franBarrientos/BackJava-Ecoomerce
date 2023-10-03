package com.treshermanitos.api.application.mapper;

import com.treshermanitos.api.domain.Customer;
import com.treshermanitos.api.application.dto.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerDtoMapper {

    CustomerDTO toDto(Customer customer);

    Customer toDomain(CustomerDTO customerDTO);
}
