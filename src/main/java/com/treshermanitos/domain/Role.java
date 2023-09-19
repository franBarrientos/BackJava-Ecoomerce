package com.treshermanitos.domain;

import java.util.List;

public class Role {
    private Long id;

    private String name;

    private List<User> users;

    private List<Privilege> privileges;
}
