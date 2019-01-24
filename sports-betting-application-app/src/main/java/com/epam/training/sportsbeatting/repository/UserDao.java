package com.epam.training.sportsbeatting.repository;

import java.util.Optional;

import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.repository.generic.CustomJpaRepository;

public interface UserDao extends CustomJpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
