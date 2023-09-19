package com.treshermanitos.role;

import jakarta.validation.constraints.NotBlank;
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
