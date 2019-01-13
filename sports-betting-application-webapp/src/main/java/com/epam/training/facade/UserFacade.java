package com.epam.training.facade;

import java.util.Optional;

import com.epam.training.dto.UserData;
import com.epam.training.dto.UserUpdateData;
import com.epam.training.sportsbeatting.domain.user.Player;

public interface UserFacade {

    Optional<Player> registerUser(UserData userData);

    Player getCurrentPlayerInfo();

    void updateUserInfo(UserUpdateData userData);

}
