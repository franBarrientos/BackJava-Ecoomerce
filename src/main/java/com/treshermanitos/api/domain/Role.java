package com.treshermanitos.api.domain;

import java.util.List;

public class Role {
    private Long id;

    private String name;

    private List<User> users;

    private List<Privilege> privileges;

    public Role(Long id, String name, List<User> users, List<Privilege> privileges) {
        this.setId(id);
        this.setName(name);
        this.setUsers(users);
        this.setPrivileges(privileges);
    }
    public Role( String name, List<Privilege> privileges) {
        this.setName(name);
        this.setPrivileges(privileges);
    }

    public Role() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
