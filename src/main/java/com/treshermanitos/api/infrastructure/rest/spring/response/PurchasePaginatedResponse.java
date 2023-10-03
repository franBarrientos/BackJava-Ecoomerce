package com.treshermanitos.api.infrastructure.rest.spring.response;

import com.treshermanitos.api.application.dto.PurchaseDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class PurchasePaginatedResponse extends PaginatedResponseBase {
    private List<PurchaseDTO> purchases;
}
