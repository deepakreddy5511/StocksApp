package com.portfolio.management.OpenFeign;

import com.portfolio.management.Dto.FundsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "FUNDS-SERVICE", path = "funds")
public interface FundsFeign {
    @GetMapping("/getAvailableFunds/{uId}")
    public ResponseEntity<Long> getavailablefunds(@PathVariable String uId);
}
