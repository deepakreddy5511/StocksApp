package com.portfolio.management.Service.impl;

import com.portfolio.management.Dto.StocksDetails;
import com.portfolio.management.Dto.UserDto;
import com.portfolio.management.Entity.Portfolio;
import com.portfolio.management.OpenFeign.FundsFeign;
import com.portfolio.management.OpenFeign.TransactionFeign;
import com.portfolio.management.OpenFeign.UserFeign;
import com.portfolio.management.Service.PortfolioService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PortfolioServiceImpl implements PortfolioService {

    private final UserFeign userFeign;
    private final FundsFeign fundsFeign;
    private final TransactionFeign transactionFeign;

    @Override
    @CircuitBreaker(name="${spring.application.name}", fallbackMethod = "getPortfolioDetils")
    public Portfolio portfolioDetails(String userId) {
        Portfolio portfolio = new Portfolio();
        ResponseEntity<Long> funds = fundsFeign.getavailablefunds(userId);
        System.out.println(funds.getBody());
        portfolio.setFunds(funds.getBody());

        ResponseEntity<UserDto> userDetails = userFeign.getUserDetails(userId);
        System.out.println(userDetails.getBody());
        portfolio.setUserDetails(userDetails.getBody());

        ResponseEntity<List<StocksDetails>> transactionDetails = transactionFeign.getStockDetails(userId);
        System.out.println(transactionDetails.getBody());
        portfolio.setStockDetails(transactionDetails.getBody());

        return portfolio;
    }

    public Portfolio getPortfolioDetils(String userId)
    {
        UserDto userDto = UserDto.builder()
                .userId("dummyUserId")
                .userName("dumyUsername")
                .adharNumber("dummyAdharNumber")
                .bankAccountNumber("dummyBankAccountNumber")
                .city("dummyCity")
                .email("dummyEmail")
                .panNumber("dummyPan")
                .password("dummyPassword")
                .phoneNumber("dummyPhone")
                .role("dummyRole")
                .build();
        Long fundsDto = Long.valueOf(000000000);

        return Portfolio.builder()
                .userDetails(userDto)
                .funds(fundsDto)
                .build();
    }
}
