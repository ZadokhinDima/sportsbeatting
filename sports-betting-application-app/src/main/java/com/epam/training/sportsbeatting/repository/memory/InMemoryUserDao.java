package com.epam.training.sportsbeatting.repository.memory;

import java.util.Optional;

import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.repository.UserDao;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserDao extends GenericInMemoryDao<User> implements UserDao {

    @Override
    public Optional<User> getUserByName(final String name) {
        return storage.values().stream().filter(user -> user.getUsername().equals(name)).findFirst();
    }
}
