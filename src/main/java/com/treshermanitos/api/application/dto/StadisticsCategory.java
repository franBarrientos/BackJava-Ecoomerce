package com.treshermanitos.api.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StadisticsCategory {
    private Long id;
    private String category;
    private Long totalSale;
}
