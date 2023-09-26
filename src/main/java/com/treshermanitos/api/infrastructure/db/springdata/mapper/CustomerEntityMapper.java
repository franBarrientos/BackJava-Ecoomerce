package com.treshermanitos.api.infrastructure.db.springdata.mapper;

import com.treshermanitos.api.domain.Customer;
import com.treshermanitos.api.infrastructure.db.springdata.entities.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {


    @Mapping(target = "user.customer", ignore = true)
    Customer toDomain(CustomerEntity customerEntity);


    CustomerEntity toEntity(Customer customer);
}
