package com.treshermanitos.api.infrastructure.db.springdata.mapper;

import java.util.function.Function;


import com.treshermanitos.api.domain.Customer;
import com.treshermanitos.api.domain.User;
import com.treshermanitos.api.infrastructure.db.springdata.entities.UserEntity;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public interface UserEntityMapper{

    User toDomain(UserEntity userEntity);

    UserEntity toEntity(User user);


}
