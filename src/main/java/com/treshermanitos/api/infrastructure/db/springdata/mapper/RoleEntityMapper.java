package com.treshermanitos.api.infrastructure.db.springdata.mapper;

import com.treshermanitos.api.domain.Customer;
import com.treshermanitos.api.domain.Privilege;
import com.treshermanitos.api.domain.Role;
import com.treshermanitos.api.domain.User;
import com.treshermanitos.api.infrastructure.db.springdata.entities.PrivilegeEntity;
import com.treshermanitos.api.infrastructure.db.springdata.entities.RoleEntity;
import com.treshermanitos.api.infrastructure.db.springdata.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleEntityMapper {


    Role toDomain(RoleEntity entity);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "customer", ignore = true)
    User toUser(UserEntity userEntity);

    @Mapping(target = "roles", ignore = true)
    Privilege toPrivilege(PrivilegeEntity privilegeEntity);


    RoleEntity toEntity(Role domain);

}
