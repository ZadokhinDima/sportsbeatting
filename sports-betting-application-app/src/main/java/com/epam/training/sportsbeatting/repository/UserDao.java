package com.epam.training.sportsbeatting.repository;

import java.util.Optional;

import com.epam.training.sportsbeatting.domain.user.User;

public interface UserDao extends GenericDao<User> {

    Optional<User> getUserByName(String name);

}
