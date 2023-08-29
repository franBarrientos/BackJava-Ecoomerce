package com.treshermanitos.treshermanitos.user;

import java.util.List;

import com.treshermanitos.treshermanitos.config.PaginatedResponseBase;


import lombok.experimental.SuperBuilder;

@SuperBuilder
public class UsersPaginatedResponse extends PaginatedResponseBase {
    private List<User> users;
}
