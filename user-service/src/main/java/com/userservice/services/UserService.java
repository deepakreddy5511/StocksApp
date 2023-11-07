package com.userservice.services;

import com.userservice.dto.LoginDto;
import com.userservice.dto.UserDto;
import com.userservice.entities.User;

public interface UserService {
    UserDto saveUser(User user);
    UserDto getUserDetailsById(String id);
    String loginUser(LoginDto loginDto);
}
