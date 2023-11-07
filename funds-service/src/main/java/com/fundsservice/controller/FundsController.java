package com.fundsservice.controller;

import com.fundsservice.dto.FundsDto;
import com.fundsservice.services.FundsService;
import com.fundsservice.entities.Funds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/funds")
public class FundsController {

    @Autowired
    private FundsService fundsService;

    @CrossOrigin
    @PostMapping(path = "/add",consumes = "application/json", produces = "application/json")
    public ResponseEntity<FundsDto> addFunds(@RequestBody Funds funds){
        return new ResponseEntity<>(fundsService.addFunds(funds), HttpStatus.CREATED);
    }

    @CrossOrigin
    @PostMapping(path= "/withdraw",consumes = "application/json", produces = "application/json")
    public ResponseEntity<FundsDto> withdrawFunds(@RequestBody Funds funds)
    {
        return new ResponseEntity<>(fundsService.withdraw(funds), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/getAvailableFunds/{uId}")
    public ResponseEntity<Long> getavailablefunds(@PathVariable("uId") String uId)
    {
        return new ResponseEntity<>(fundsService.getFunds(uId), HttpStatus.OK);
    }

}
