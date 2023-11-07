package com.portfolio.management.OpenFeign;

import com.portfolio.management.Dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE", path = "/user")
public interface UserFeign {
    @GetMapping("/details/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable("id") String userId);
}
