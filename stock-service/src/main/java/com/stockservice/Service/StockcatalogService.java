package com.stockservice.Service;

import com.stockservice.Entity.StocksDetails;

import java.util.List;

public interface StockcatalogService {
    StocksDetails createStock(StocksDetails stocksDetails);
    List<StocksDetails> getAllStocks();
}
