package com.treshermanitos.purchase;

import com.treshermanitos.config.PaginatedResponseBase;
import com.treshermanitos.purchase.dto.PurchaseDTO;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class PurchasesPaginatedResponse extends PaginatedResponseBase {
    List<PurchaseDTO> purchases;
}
