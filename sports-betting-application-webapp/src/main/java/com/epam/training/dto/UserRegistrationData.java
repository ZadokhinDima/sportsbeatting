package com.epam.training.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.epam.training.sportsbeatting.domain.user.Player;

@Data
public class UserRegistrationData {

    @Email
    private String username;
    @Size(min = 8)
    private String password;
    @Pattern(regexp = "[А-Я][а-я]+\\s[А-Я][а-я]+|[A-Z][a-z]+\\s[A-Z][a-z]+")
    private String name;
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    private String birthDate;
    @Pattern(regexp = "^\\d{10}")
    private String accountNumber;
    @Pattern(regexp = "^[1-9][0-9]*$")
    private String balance;
    private Player.Currency currency;

}
