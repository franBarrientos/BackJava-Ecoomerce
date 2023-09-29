package com.treshermanitos.api.infrastructure.db.springdata.mapper;


import com.treshermanitos.api.domain.Privilege;
import com.treshermanitos.api.domain.Role;
import com.treshermanitos.api.domain.User;
import com.treshermanitos.api.infrastructure.db.springdata.entities.PrivilegeEntity;
import com.treshermanitos.api.infrastructure.db.springdata.entities.RoleEntity;
import com.treshermanitos.api.infrastructure.db.springdata.entities.UserEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    @Mapping(target = "customer.user", ignore = true)
    User toDomain(UserEntity userEntity);

    @Mapping(target = "users", ignore = true)
    Role toRole(RoleEntity roleEntity);

    @Mapping(target = "roles", ignore = true)
    Privilege toPrivilege(PrivilegeEntity privilegeEntity);


    UserEntity toEntity(User user);


}
