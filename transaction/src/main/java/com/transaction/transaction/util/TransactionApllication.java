package com.transaction.transaction.util;

import com.transaction.transaction.Enitity.Funds;
import com.transaction.transaction.Enitity.Portfolio;
import com.transaction.transaction.dto.FundsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "FUNDS-SERVICE",path = "/funds")
public interface TransactionApllication {

    @GetMapping("/getAvailableFunds/{uId}")
    public Long getavailablefunds(@PathVariable String uId);

    @PostMapping("/withdraw")
    public FundsDto withdrawFunds(@RequestBody Funds funds);

    @PostMapping("/add")
    public FundsDto addFunds(@RequestBody Funds funds);


}


