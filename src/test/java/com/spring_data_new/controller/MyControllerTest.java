package com.spring_data_new.controller;

import com.spring_data_new.model.User;
import com.spring_data_new.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MyControllerTest {
    @InjectMocks
    private MyController myController;

    @Mock
    private UserService userService;


    @Test
    void testHello() {
        assertEquals("hell", myController.hello(new ConcurrentModel()));
    }


    @Test
    void testAddUser() {
        User user = new User();
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        when(userService.saveUser((User) any())).thenReturn(user);
        ConcurrentModel model = new ConcurrentModel();

        User user1 = new User();
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        assertEquals("result", myController.addUser(model, user1));
        verify(userService).saveUser((User) any());
    }


    @Test
    void shouldReturnNoUsersPageIfNoUsersFound() {
        when(userService.findAll()).thenReturn(new ArrayList<>());
        assertEquals("noUsers", myController.getAllUsers(new ConcurrentModel()));
        verify(userService).findAll();
    }


    @Test
    void shouldReturnAllUsersPageWhenFoundAtLeastOneUser() {
        User user = new User();
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userService.findAll()).thenReturn(userList);
        assertEquals("allUsers", myController.getAllUsers(new ConcurrentModel()));
        verify(userService, atLeast(1)).findAll();
    }
}

