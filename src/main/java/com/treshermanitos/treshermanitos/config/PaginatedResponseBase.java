package com.treshermanitos.treshermanitos.config;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public abstract class PaginatedResponseBase {
    protected int totalItems;
    protected int totalPages;
}
