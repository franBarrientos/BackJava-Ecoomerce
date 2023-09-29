package com.treshermanitos.api.infrastructure.rest.spring.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    private Set<ProductDTO> products;

    private Boolean state;

}
