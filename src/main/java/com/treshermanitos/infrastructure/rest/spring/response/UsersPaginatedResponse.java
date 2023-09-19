package com.treshermanitos.infrastructure.rest.spring.response;

import java.util.List;

import com.treshermanitos.config.PaginatedResponseBase;


import com.treshermanitos.infrastructure.rest.spring.dto.UserDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class UsersPaginatedResponse extends PaginatedResponseBase {
    private List<UserDTO> users;

}
