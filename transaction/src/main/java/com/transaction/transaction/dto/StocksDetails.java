package com.transaction.transaction.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StocksDetails {
    private String id;
    private String stockName;
    private String stockCode;
    private int currentStockPrice;
    private int unitsAvailable;

}
