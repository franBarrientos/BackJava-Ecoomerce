package com.treshermanitos.api.infrastructure.db.springdata.mapper;

import com.treshermanitos.api.domain.Privilege;
import com.treshermanitos.api.domain.Role;
import com.treshermanitos.api.infrastructure.db.springdata.entities.PrivilegeEntity;
import com.treshermanitos.api.infrastructure.db.springdata.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PrivilegeEntityMapper {
    Privilege toDomain(PrivilegeEntity privilegeEntity);

    @Mapping(target = "privileges", ignore = true)
    @Mapping(target = "users", ignore = true)
    Role toRole(RoleEntity roleEntity);


    PrivilegeEntity toEntity(Privilege privilege);

}
