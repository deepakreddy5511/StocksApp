package com.fundsservice.services;

import com.fundsservice.dto.FundsDto;
import com.fundsservice.dto.UserDetailsDto;
import com.fundsservice.entities.Funds;

public interface FundsService {
    FundsDto addFunds(Funds fund);
    FundsDto withdraw(Funds fund);
    Long getFunds(String uId);
}
