package com.epam.training.sportsbeatting.service.impl;

import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.repository.UserDao;
import com.epam.training.sportsbeatting.service.SessionContextService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionContextServiceImpl implements SessionContextService {

    @Autowired
    private UserDao userDao;

    private User sessionUser;

    @Override
    public User getSessionUser() {
        userDao.refresh(sessionUser);
        return sessionUser;
    }

    @Override
    public void setSessionUser(final User user) {
        sessionUser = user;
    }
}
