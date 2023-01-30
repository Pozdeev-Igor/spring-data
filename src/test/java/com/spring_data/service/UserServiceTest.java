package com.spring_data.service;

import com.spring_data.model.User;
import com.spring_data.repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserService userService;

    /**
     * Method under test: {@link UserService#saveNewUser(String, String)}
     */
    @Test
    void testSaveNewUser() {
        User user = new User();
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        when(userRepo.save(any())).thenReturn(user);
        assertSame(user, userService.saveNewUser("Jane", "Doe"));
        verify(userRepo).save(any());
    }

    /**
     * Method under test: {@link UserService#getAll()}
     */
    @Test
    void testGetAll() {
        ArrayList<User> userList = new ArrayList<>();
        when(userRepo.findAll()).thenReturn(userList);
        List<User> actualAll = userService.getAll();
        assertSame(userList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(userRepo).findAll();
    }
}

