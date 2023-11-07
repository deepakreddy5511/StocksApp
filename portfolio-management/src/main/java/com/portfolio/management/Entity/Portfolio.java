package com.portfolio.management.Entity;

import com.portfolio.management.Dto.StocksDetails;
import com.portfolio.management.Dto.UserDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Portfolio {
    UserDto userDetails;
    Long funds;
    List<StocksDetails> stockDetails;
}
