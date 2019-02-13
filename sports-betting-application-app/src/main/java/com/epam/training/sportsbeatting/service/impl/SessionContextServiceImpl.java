package com.epam.training.sportsbeatting.service.impl;

import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.repository.UserDao;
import com.epam.training.sportsbeatting.security.ApplicationUserDetails;
import com.epam.training.sportsbeatting.service.SessionContextService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SessionContextServiceImpl implements SessionContextService {

    private static final String USERNAME_NOT_FOUND_MESSAGE = "User with name %s not found.";

    @Autowired
    private UserDao userDao;

    @Override
    public User getSessionUser() {
        final ApplicationUserDetails userDetails = (ApplicationUserDetails)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDao.findByUsername(userDetails.getUsername()).orElseThrow(() ->
                new UsernameNotFoundException(String.format(USERNAME_NOT_FOUND_MESSAGE, userDetails.getUsername())));
    }
}
