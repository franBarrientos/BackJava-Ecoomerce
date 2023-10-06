package com.treshermanitos.api.application.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String img;

    private Boolean state;

}
