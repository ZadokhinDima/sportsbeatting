package com.epam.training.sportsbeatting.service.impl;

import com.epam.training.sportsbeatting.domain.user.User;
import com.epam.training.sportsbeatting.repository.UserDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SessionContextServiceImplTest {

    @Mock
    private UserDao userDao;

    @InjectMocks
    private SessionContextServiceImpl testingInstance;

    @Test
    public void shouldSaveAndReturnUserFromSession() {
        final User user = mock(User.class);

        testingInstance.setSessionUser(user);
        final User sessionUser = testingInstance.getSessionUser();

        assertEquals(user, sessionUser);
        verify(userDao).refresh(user);
    }

}