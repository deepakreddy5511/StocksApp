package com.fundsservice.services.impl;

import com.fundsservice.dto.UserDetailsDto;
import com.fundsservice.openfeign.UserFeignClient;
import com.fundsservice.repository.FundsRepository;
import com.fundsservice.services.FundsService;
import com.fundsservice.dto.FundsDto;
import com.fundsservice.entities.Funds;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FundsServiceImpl implements FundsService {
    @Autowired
    private FundsRepository fundsRepository;

    private final UserFeignClient userFeignClient;
//    private final FundsMapper fundsMapper;

    @Override
    public FundsDto addFunds(Funds funds) {
       Optional<Funds> data = Optional.ofNullable(fundsRepository.findById(funds.getUserId()).orElse(null));
        log.info("Inside add funds");

        ResponseEntity<String> loginStatus = userFeignClient.loginUser(funds);
        FundsDto amountAdd = null;
        if(loginStatus.getBody().equals("Login success"))
        {
            if(data.isEmpty())
            {
                funds.setStatus("Added funds successfully");
                amountAdd = mapToDto(fundsRepository.save(funds));
                return amountAdd;
            }
            else{
                funds.setAmount(data.get().getAmount() + funds.getAmount());
                funds.setStatus("Added funds successfully");
                return mapToDto(fundsRepository.save(funds));
            }
        }
        else{
            funds.setStatus("Fund add unsuccessfull, please check your userId or password");
            return mapToDto(funds);
        }
    }

    @Override
    public FundsDto withdraw(Funds funds) {
        log.info("Inside withdraw funds");
        ResponseEntity<String> loginStatus = userFeignClient.loginUser(funds);
        Long amount = Long.valueOf(0);
        if(loginStatus.getBody().equals("Login success")) {
            if (fundsRepository.findById(funds.getUserId()).isPresent()) {
                Optional<Funds> data = fundsRepository.findById(funds.getUserId());
                if (data.get().getAmount() > funds.getAmount())
                    amount = data.get().getAmount() - funds.getAmount();
                    funds.setStatus("Successfully withdrawed "+funds.getAmount()+ " Rs");
            }
        }
        else
        {
            funds.setStatus("Withdraw unsuccessfull, please check your userId or password");
        }
        funds.setAmount(amount);
        return mapToDto(fundsRepository.save(funds));
    }

    @Override
    public Long getFunds(String uId) {
        Optional<Funds> details = fundsRepository.findById(uId);
            return details.get().getAmount();
    }

    //    @Override
//    public Long getFunds(UserDetailsDto userDetailsDto)
//    {
//       //ResponseEntity<String> loginStatus = userFeignClient.loginUser(userDetailsDto);
//        if(loginStatus.getBody().equals("Login success")) {
//        return null;
//    }
    private FundsDto mapToDto(Funds funds) {
        return FundsDto.builder()
                .userId(funds.getUserId())
                .amount(funds.getAmount())
                .status(funds.getStatus())
                .build();
    }
}
