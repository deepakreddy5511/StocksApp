package com.userservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
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
