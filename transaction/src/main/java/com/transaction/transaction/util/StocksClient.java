package com.transaction.transaction.util;

import com.transaction.transaction.dto.StocksDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "STOCKS-SERVICE",path = "stocks")
public interface StocksClient {
    @GetMapping("/getAStocks/{code}")
    public StocksDetails getAStock(@PathVariable String code);

    @PostMapping("/editStocks")
    public StocksDetails editStocks(@RequestBody StocksDetails stocksDetails);
}
