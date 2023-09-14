package com.treshermanitos.treshermanitos.purchase;

import com.treshermanitos.treshermanitos.config.PaginatedResponseBase;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Data
public class PurchasesPaginatedResponse extends PaginatedResponseBase {
    List<? extends Object> purchases;
}
