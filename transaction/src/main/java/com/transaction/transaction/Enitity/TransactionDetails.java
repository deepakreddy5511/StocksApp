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
@Table(name = "Transactiondetails_1")
public class TransactionDetails {
    @Id
    @Column(name="transactionId")
    private String transactionId;
    @Column(name="id")
    private String id;
    @Column(name="password")
    private String password;
    @Column(name="stockName")
    private String stockName;
    @Column(name="stockCode")
    private String stockCode;
    @Column(name="currentStockPrice")
    private Integer currentStockPrice;
    @Column(name="noOfUnitsToBuy")
    private Integer noOfUnitsToBuy;
    @Column(name="noOfUnitsToSell")
    private Integer noOfUnitsToSell;
    @Column(name="amountPaid")
    private Long amountPaid;
    @Column(name="errorMessage")
    private String errorMessage;
    @Column(name="status")
    private String status;
}
