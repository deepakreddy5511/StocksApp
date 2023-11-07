package com.transaction.transaction.Enitity;

import com.transaction.transaction.dto.FundsDto;
import com.transaction.transaction.dto.StocksDetails;
import com.transaction.transaction.dto.UserDetailsDto;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Portfolio {
    UserDetailsDto userDetails;
    FundsDto fundsDetails;
    StocksDetails stockDetails;
}
