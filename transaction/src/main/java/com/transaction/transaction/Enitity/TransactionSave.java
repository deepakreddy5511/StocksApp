package com.transaction.transaction.Enitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "User_stock_details_2")
public class TransactionSave {
    @Id
    @Column(name="stockCode")
    private String stockCode;
    @Column(name="id")
    private String id;
    @Column(name="stockName")
    private String stockName;
    @Column(name="currentStockPrice")
    private int currentStockPrice;
    @Column(name="noOfUnits")
    private int noOfUnits;
}
