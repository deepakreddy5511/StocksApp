package com.stockservice.Repository;

import com.stockservice.Entity.StocksDetails;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StockCatalogRepository extends JpaRepository<StocksDetails, String> {
    StocksDetails findByStockCode(String code);
}
