package com.stockservice.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import jakarta.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Table(schema = "stocks_details")
public class StocksDetails {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="stockname")
    private String stockName;

    @Column(name="stockcode")
    private String stockCode;

    @Column(name="currentStockPrice")
    private int currentStockPrice;

    @Column(name="unitsAvailable")
    private int unitsAvailable;
}
