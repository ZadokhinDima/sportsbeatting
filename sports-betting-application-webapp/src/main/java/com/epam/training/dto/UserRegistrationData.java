package com.epam.training.dto;

import lombok.Data;

import com.epam.training.sportsbeatting.domain.user.Player;

@Data
public class UserRegistrationData {

    private String username;
    private String password;
    private String name;
    private String birthDate;
    private String accountNumber;
    private String balance;
    private Player.Currency currency;

}
