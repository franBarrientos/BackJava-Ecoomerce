package com.treshermanitos.api.purchase;

import com.treshermanitos.infrastructure.config.spring.PaginatedResponseBase;
import com.treshermanitos.api.purchase.dto.PurchaseDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class PurchasesPaginatedResponse extends PaginatedResponseBase {
    List<PurchaseDTO> purchases;
}
