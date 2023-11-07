package com.stockservice.Controller;

import com.stockservice.Entity.StocksDetails;
import com.stockservice.Service.imp.StockCatalogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/stocks")
public class StocksCatalogController {
    @Autowired
    StockCatalogServiceImp stockCatalogService;

    @CrossOrigin
    @PostMapping(path = "/createStocks",consumes = "application/json", produces = "application/json")
    public ResponseEntity<StocksDetails> createStocks(@RequestBody StocksDetails stocksDetails)
    {
        return new ResponseEntity<>(stockCatalogService.createStock(stocksDetails), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/getStocks")
    public ResponseEntity<List<StocksDetails>> getAllStocks()
    {
        return new ResponseEntity<>(stockCatalogService.getAllStocks(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/getAStocks/{code}")
    public ResponseEntity<StocksDetails> getAStock(@PathVariable("code") String code)
    {
        return new ResponseEntity<>(stockCatalogService.getAStock(code), HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping(path = "/editStocks",consumes = "application/json", produces = "application/json")
    public ResponseEntity<StocksDetails> editStocks(@RequestBody StocksDetails stocksDetails)
    {
        return new ResponseEntity<>(stockCatalogService.editStock(stocksDetails), HttpStatus.OK);
    }
}
