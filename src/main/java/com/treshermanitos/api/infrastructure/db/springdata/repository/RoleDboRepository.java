package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.application.repository.RoleRepository;
import com.treshermanitos.api.domain.Role;
import com.treshermanitos.api.infrastructure.db.springdata.entities.RoleEntity;
import com.treshermanitos.api.infrastructure.db.springdata.mapper.RoleEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RoleDboRepository implements RoleRepository {

    private final SpringDataRoleRepostory dataRoleRepository;
    public final RoleEntityMapper roleEntityMapper;

    @Override
    public Optional<Role> findRoleByName(String name) {
        RoleEntity role = this.dataRoleRepository.findRoleByName(name);
        return role != null?
                Optional.of(this.roleEntityMapper.toDomain(role))
                :
                Optional.empty();
    }

    @Override
    public Role save(Role product) {
        return this.roleEntityMapper
                .toDomain(this.dataRoleRepository
                        .save(this.roleEntityMapper
                                .toEntity(product)));
    }
}
