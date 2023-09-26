package com.treshermanitos.api.domain;

import java.util.Collection;

public class Privilege {
    private Long id;

    private String name;

    private Collection<Role> roles;


    public Privilege(String name) {
        this.name = name;
    }
    public Privilege() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long p_id) {
        id = p_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String p_name) {
        name = p_name;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> p_roles) {
        roles = p_roles;
    }
}
