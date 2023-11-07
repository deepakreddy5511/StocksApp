package com.portfolio.management.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDetailsDto {
    private String id;
    private String stockName;
    private String stockCode;
    private int currentStockPrice;
}
