package com.treshermanitos.treshermanitos.config;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class PaginatedResponseBase {
    protected int totalItems;
    protected int totalPages;
}
