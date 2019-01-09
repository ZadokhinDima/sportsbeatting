package com.epam.training.facade;

import java.util.Optional;

import com.epam.training.dto.UserRegistrationData;
import com.epam.training.sportsbeatting.domain.user.Player;

public interface UserFacade {

    Optional<Player> registerUser(UserRegistrationData userRegistrationData);

}
