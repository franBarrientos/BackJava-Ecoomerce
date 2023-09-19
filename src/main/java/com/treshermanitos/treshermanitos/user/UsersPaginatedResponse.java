package com.treshermanitos.treshermanitos.user;

import java.util.List;

import com.treshermanitos.treshermanitos.config.PaginatedResponseBase;


import com.treshermanitos.treshermanitos.user.dto.UserDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class UsersPaginatedResponse extends PaginatedResponseBase {
    private List<UserDTO> users;

}
