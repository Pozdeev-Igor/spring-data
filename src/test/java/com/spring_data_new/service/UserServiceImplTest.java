package com.spring_data_new.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.spring_data_new.model.User;
import com.spring_data_new.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void testFindAll() {
        ArrayList<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        userList.add(new User());
        when(userRepo.findAll()).thenReturn(userList);
        List<User> actualFindAllResult = userServiceImpl.findAll();
        assertSame(userList, actualFindAllResult);
        assertFalse(actualFindAllResult.isEmpty());
        assertEquals(3, actualFindAllResult.size());
        verify(userRepo).findAll();
    }


    @Test
    void testSaveUser() {
        User user = new User();
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        when(userRepo.save(any())).thenReturn(user);

        User user1 = new User();
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        assertSame(user, userServiceImpl.saveUser(user1));
        verify(userRepo).save(any());
    }
}

