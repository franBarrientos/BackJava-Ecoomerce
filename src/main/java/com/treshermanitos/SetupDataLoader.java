package com.treshermanitos;

import com.treshermanitos.privilege.Privilege;
import com.treshermanitos.privilege.PrivilegeRepository;
import com.treshermanitos.role.Role;
import com.treshermanitos.role.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    private final RoleRepository roleRepository;
    private final PrivilegeRepository privilegeRepository;


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (this.alreadySetup)
            return;
        //admin
        Privilege readPrivilegeAdmin
                = createPrivilegeIfNotFound("ADMIN:READ");
        Privilege writePrivilegeAdmin
                = createPrivilegeIfNotFound("ADMIN:WRITE");
        Privilege updatePrivilegeAdmin
                = createPrivilegeIfNotFound("ADMIN:UPDATE");
        Privilege deletePrivilegeAdmin
                = createPrivilegeIfNotFound("ADMIN:DELETE");
        //user

        Privilege readPrivilegeUser
                = createPrivilegeIfNotFound("USER:READ");
        Privilege writePrivilegeUser
                = createPrivilegeIfNotFound("USER:WRITE");
        Privilege updatePrivilegeUser
                = createPrivilegeIfNotFound("USER:UPDATE");
        Privilege deletePrivilegeUser
                = createPrivilegeIfNotFound("USER:DELETE");


        List<Privilege> adminPrivileges = List.of(
                readPrivilegeAdmin,
                writePrivilegeAdmin,
                updatePrivilegeAdmin,
                deletePrivilegeAdmin);

        List<Privilege> userPrivileges = List.of(
                readPrivilegeUser,
                writePrivilegeUser,
                updatePrivilegeUser,
                deletePrivilegeUser);

        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", userPrivileges);
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
    public Privilege createPrivilegeIfNotFound(String name) {

        Privilege privilege = privilegeRepository.findPrivilegeByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }
    @Transactional
    public Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {

        Role role = roleRepository.findRoleByName(name);
        if (role == null) {
            role = new Role(name, privileges);
            roleRepository.save(role);
        }
        return role;
    }
}



