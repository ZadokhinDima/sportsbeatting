package com.epam.training.dto;

import lombok.Data;

@Data
public class UserUpdateData {

    private Long id;
    private String name;
    private String accountNumber;
    private String newPassword;

}
