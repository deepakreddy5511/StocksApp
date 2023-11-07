package com.userservice.controller;

import com.userservice.dto.LoginDto;
import com.userservice.dto.UserDto;
import com.userservice.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private com.userservice.services.UserService UserService;

    @CrossOrigin
    @PostMapping(path="/create" ,consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDto> createUser(@RequestBody User user){
        return new ResponseEntity<>(UserService.saveUser(user), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping("/details/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable("id") String userId) {
        UserDto apiResponseDto = UserService.getUserDetailsById(userId);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.valueOf(200));
    }

    @CrossOrigin
    @PostMapping(path= "/login",consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(UserService.loginUser(loginDto), HttpStatus.valueOf(200));
    }
}
