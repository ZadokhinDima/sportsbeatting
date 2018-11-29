package com.epam.training.sportsbeatting.repository.memory;

import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.repository.UserDao;

import org.springframework.stereotype.Repository;

@Repository
public class InMemoryUserDao extends GenericInMemoryDao<User> implements UserDao {

}
