package com.treshermanitos.api.infrastructure.db.springdata.repository;

import com.treshermanitos.api.application.repository.PrivilegeRepository;
import com.treshermanitos.api.domain.Privilege;
import com.treshermanitos.api.infrastructure.db.springdata.entities.PrivilegeEntity;
import com.treshermanitos.api.infrastructure.db.springdata.mapper.PrivilegeEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PrivilegeDboRepository implements PrivilegeRepository {

    private final SpringDataPrivilegeRepository dataPrivilegeRepository;
    private final PrivilegeEntityMapper privilegeEntityMapper;

    @Override
    public Optional<Privilege> findPrivilegeByName(String name) {
        PrivilegeEntity privilege = this.dataPrivilegeRepository.findPrivilegeByName(name);
        return privilege != null?
                Optional.of(this.privilegeEntityMapper
                        .toDomain(privilege))
                :
                Optional.empty();
    }

    @Override
    public Privilege save(Privilege privilege) {
        return this.privilegeEntityMapper
                .toDomain(this.privilegeEntityMapper
                        .toEntity(privilege));
    }
}
