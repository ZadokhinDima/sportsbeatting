package com.epam.training.sportsbeatting.service.impl;

import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.service.SessionContextService;

import org.springframework.stereotype.Service;

@Service
public class SessionContextServiceImpl implements SessionContextService {

    private User sessionUser;

    @Override
    public User getSessionUser() {
        return sessionUser;
    }

    @Override
    public void setSessionUser(final User user) {
        sessionUser = user;
    }
}
