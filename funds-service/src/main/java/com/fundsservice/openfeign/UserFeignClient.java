package com.fundsservice.openfeign;

import com.fundsservice.dto.FundsDto;
import com.fundsservice.entities.Funds;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "USER-SERVICE", path = "user")
public interface UserFeignClient {
    @GetMapping("/login")
    ResponseEntity<String> loginUser(@RequestBody Funds funds);
}
