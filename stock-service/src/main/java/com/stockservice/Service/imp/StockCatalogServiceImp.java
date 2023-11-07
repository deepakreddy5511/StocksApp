package com.stockservice.Service.imp;

import com.stockservice.Entity.StocksDetails;
import com.stockservice.Repository.StockCatalogRepository;
import com.stockservice.Service.StockcatalogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class StockCatalogServiceImp implements StockcatalogService {

    @Autowired
    StockCatalogRepository stockCatalogRepository;

    @Override
    public StocksDetails createStock(StocksDetails stocksDetails)
    {
        log.info("Inside stocks");
        stocksDetails.setId(UUID.randomUUID().toString());
        stockCatalogRepository.save(stocksDetails);
        return stocksDetails;
    }

    @Override
    public List<StocksDetails> getAllStocks() {
        return stockCatalogRepository.findAll();
    }
    public StocksDetails getAStock(String code) {
        log.info("Inside stocks");
        StocksDetails stocksDetails = stockCatalogRepository.findByStockCode(code);
        return stocksDetails;
    }

    public StocksDetails editStock(StocksDetails stocksDetails)
    {
        log.info("Inside stocks");
        StocksDetails stock = stockCatalogRepository.findByStockCode(stocksDetails.getStockCode());
        stock.setUnitsAvailable(stocksDetails.getUnitsAvailable());
        return stockCatalogRepository.save(stock);

    }

}
