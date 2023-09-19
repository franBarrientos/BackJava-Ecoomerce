package com.treshermanitos.treshermanitos.purchase;

import com.treshermanitos.treshermanitos.config.PaginatedResponseBase;
import com.treshermanitos.treshermanitos.purchase.dto.PurchaseDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class PurchasesPaginatedResponse extends PaginatedResponseBase {
    List<PurchaseDTO> purchases;
}
