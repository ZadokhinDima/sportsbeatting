package com.epam.training.sportsbeatting.repository.memory;

import java.util.Optional;

import javax.annotation.PostConstruct;

import com.epam.training.sportsbeatting.domain.user.Player;
import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.repository.UserDao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserDao extends GenericInMemoryDao<User> implements UserDao {

    @Override
    public Optional<User> getUserByName(final String name) {
        return storage.values().stream().filter(user -> user.getUsername().equals(name)).findFirst();
    }

    @PostConstruct
    private void init() {
        User player = Player.builder()
                .balance(1000)
                .accountNumber("accountNumber")
                .username("user")
                .password(new BCryptPasswordEncoder().encode("user"))
                .currency(Player.Currency.UAH)
                .enabled(true)
                .build();
        save(player);
    }
}
