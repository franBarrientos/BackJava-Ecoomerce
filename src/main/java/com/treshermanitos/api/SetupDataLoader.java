package com.treshermanitos.api;

import com.treshermanitos.api.application.repository.PrivilegeRepository;
import com.treshermanitos.api.application.repository.RoleRepository;
import com.treshermanitos.api.domain.Privilege;
import com.treshermanitos.api.domain.Role;
import com.treshermanitos.api.infrastructure.db.springdata.mapper.PrivilegeEntityMapper;
import com.treshermanitos.api.infrastructure.db.springdata.mapper.RoleEntityMapper;
import com.treshermanitos.api.infrastructure.db.springdata.entities.PrivilegeEntity;
import com.treshermanitos.api.infrastructure.db.springdata.entities.RoleEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;
    private final RoleEntityMapper roleEntityMapper;
    private final PrivilegeEntityMapper privilegeEntityMapper;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (this.alreadySetup)
            return;
        //admin
        PrivilegeEntity readPrivilegeAdminEntity
                = createPrivilegeIfNotFound("ADMIN:READ");
        PrivilegeEntity writePrivilegeAdminEntity
                = createPrivilegeIfNotFound("ADMIN:WRITE");
        PrivilegeEntity updatePrivilegeAdminEntity
                = createPrivilegeIfNotFound("ADMIN:UPDATE");
        PrivilegeEntity deletePrivilegeAdminEntity
                = createPrivilegeIfNotFound("ADMIN:DELETE");
        PrivilegeEntity DASDASDSA
                = createPrivilegeIfNotFound("ADMIN:DELETE");
        Set<PrivilegeEntity> pps = new HashSet<>(){{
            add(readPrivilegeAdminEntity);
            add(writePrivilegeAdminEntity);
            add(updatePrivilegeAdminEntity);
            add(deletePrivilegeAdminEntity);
        }};



        PrivilegeEntity readPrivilegeUserEntity
                = createPrivilegeIfNotFound("USER:READ");
        PrivilegeEntity writePrivilegeUserEntity
                = createPrivilegeIfNotFound("USER:WRITE");
        PrivilegeEntity updatePrivilegeUserEntity
                = createPrivilegeIfNotFound("USER:UPDATE");
        PrivilegeEntity deletePrivilegeUserEntity
                = createPrivilegeIfNotFound("USER:DELETE");


        List<PrivilegeEntity> adminPrivilegeEntities = List.of(
                readPrivilegeAdminEntity,
                writePrivilegeAdminEntity,
                updatePrivilegeAdminEntity,
                deletePrivilegeAdminEntity);

        List<PrivilegeEntity> userPrivilegeEntities = List.of(
                readPrivilegeUserEntity,
                writePrivilegeUserEntity,
                updatePrivilegeUserEntity,
                deletePrivilegeUserEntity);

        createRoleIfNotFound("ROLE_ADMIN", adminPrivilegeEntities);
        createRoleIfNotFound("ROLE_USER", userPrivilegeEntities);
/*
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setPassword(passwordEncoder.encode("test"));
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);*/

        alreadySetup = true;
    }


    @Transactional
    public PrivilegeEntity createPrivilegeIfNotFound(String name) {

        Optional<Privilege> privilegeEntity = privilegeRepository
                .findPrivilegeByName(name);

        if (privilegeEntity.isEmpty()) {
            Privilege privilegeNew = new Privilege(name);
            return this.privilegeEntityMapper
                    .toEntity(privilegeRepository.save(privilegeNew));
        }else {
            return this.privilegeEntityMapper
                    .toEntity(privilegeEntity.get());
        }

    }


    @Transactional
    public RoleEntity createRoleIfNotFound(
            String name, Collection<PrivilegeEntity> privilegeEntities) {

        Optional<Role> role = roleRepository.findRoleByName(name);

        if (role.isEmpty()) {
            Role roleNew = new Role(name, privilegeEntities
                    .stream()
                    .map(this.privilegeEntityMapper::toDomain)
                    .collect(Collectors.toList())
            );
            return this.roleEntityMapper
                    .toEntity(this.roleRepository.save(roleNew));
        }else {
            return this.roleEntityMapper.toEntity(role.get());
        }
    }
}



