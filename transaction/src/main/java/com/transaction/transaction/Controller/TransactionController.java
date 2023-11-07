package com.transaction.transaction.Controller;

import com.netflix.discovery.EurekaClient;
import com.transaction.transaction.Enitity.TransactionDetails;
import com.transaction.transaction.Enitity.TransactionSave;
import com.transaction.transaction.Service.TransactionService;
import com.transaction.transaction.dto.StocksDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value= "/buy")
public class TransactionController {
        @Autowired
        TransactionService transactionService;

        @CrossOrigin
        @PostMapping(path="/buyStocks", consumes = "application/json", produces = "application/json")
        public ResponseEntity<TransactionDetails> buyStocks(@RequestBody TransactionDetails transactionDetails)
        {
            return new ResponseEntity<>(transactionService.buyStock(transactionDetails), HttpStatus.OK);
        }

        @CrossOrigin
        @PostMapping(path= "/sellStocks", consumes = "application/json", produces = "application/json")
        public ResponseEntity<TransactionDetails> sellStocks(@RequestBody TransactionDetails transactionDetails)
        {
                return new ResponseEntity<>(transactionService.sellStock(transactionDetails), HttpStatus.OK);
        }

        @CrossOrigin
        @GetMapping("/getStockDetailsByuserId/{uId}")
        public ResponseEntity<List<StocksDetails>> getStockDetails(@PathVariable("uId") String uId)
        {
                return new ResponseEntity<>(transactionService.getStockDetailsById(uId), HttpStatus.OK);
        }
}
