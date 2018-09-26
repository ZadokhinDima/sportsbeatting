package com.epam.training.sportsbeatting.domain.user;

import com.epam.training.sportsbeatting.domain.user.usergroup.UserGroup;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Admin extends User{
    private UserGroup userGroup;
}
