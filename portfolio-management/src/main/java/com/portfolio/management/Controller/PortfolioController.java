package com.portfolio.management.Controller;


import com.portfolio.management.Entity.Portfolio;
import com.portfolio.management.Service.PortfolioService;
import com.portfolio.management.Service.impl.PortfolioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/portfolio")
public class PortfolioController {

    @Autowired
    PortfolioServiceImpl portfolioDetails;

    @CrossOrigin
    @GetMapping("/details/{userId}")
    public ResponseEntity<Portfolio> portfolioManagement(@PathVariable("userId") String userId){
        return new ResponseEntity<>(portfolioDetails.portfolioDetails(userId),HttpStatus.OK);
    }
}
