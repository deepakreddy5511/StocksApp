package com.portfolio.management.OpenFeign;

import com.portfolio.management.Dto.StocksDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "TRANSACTION-SERVICE", path = "buy")
public interface TransactionFeign {
    @GetMapping("/getStockDetailsByuserId/{uId}")
    public ResponseEntity<List<StocksDetails>> getStockDetails(@PathVariable String uId);
}
