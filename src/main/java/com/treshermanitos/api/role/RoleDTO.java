package com.treshermanitos.api.role;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class RoleDTO {
    private Long id;

    private String name;

    public RoleDTO(Long id, String name) {
        this.setId(id);
        this.setName(name);
    }

}
