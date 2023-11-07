package com.portfolio.management.Dto;

import lombok.*;

@Setter
@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    String userId;
    String userName;
    String phoneNumber;
    String email;
    String panNumber;
    String adharNumber;
    String city;
    String bankAccountNumber;
    String role;
    String password;
}
