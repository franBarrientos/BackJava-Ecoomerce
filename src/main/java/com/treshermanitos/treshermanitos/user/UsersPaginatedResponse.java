package com.treshermanitos.treshermanitos.user;

import java.util.List;

import com.treshermanitos.treshermanitos.config.PaginatedResponseBase;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class UsersPaginatedResponse extends PaginatedResponseBase {
    private List<UserDTO> users;
}
