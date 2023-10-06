package com.treshermanitos.api.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Category {
    private Long id;

    private String name;

    private String img;

    private Date createdAt;

    private Date updatedAt;

    private Boolean state;
}
