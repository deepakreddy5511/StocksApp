package com.portfolio.management.Dto;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StocksDetails {
    private String stockCode;
    private String id;
    private String stockName;
    private int currentStockPrice;
    private int noOfUnits;
}
