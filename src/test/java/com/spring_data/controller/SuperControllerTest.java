package com.spring_data.controller;

import com.spring_data.model.User;
import com.spring_data.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SuperControllerTest {
    @InjectMocks
    private SuperController superController;

    @Mock
    private UserService userService;


    @Test
    void testHello() {
        ModelAndView modelAndView = new ModelAndView("View Name");
        ModelAndView actualHelloResult = superController.hello(modelAndView);
        assertSame(modelAndView, actualHelloResult);
        assertTrue(actualHelloResult.isReference());
        assertSame(actualHelloResult.getModel(), actualHelloResult.getModelMap());
    }

    @Test
    void testHello3() {
        ModelAndView modelAndView = mock(ModelAndView.class);
        when(modelAndView.addObject(any())).thenReturn(new ModelAndView("View Name"));
        doNothing().when(modelAndView).setViewName(any());
        superController.hello(modelAndView);
        verify(modelAndView).addObject(any());
        verify(modelAndView).setViewName(any());
    }

    @Test
    void testAddUser() {
        User user = new User();
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        when(userService.saveNewUser(any(), any())).thenReturn(user);
        ModelAndView modelAndView = new ModelAndView("viewName");
        ModelAndView actualAddUserResult = superController.addUser("Jane", "Doe", modelAndView);
        assertSame(modelAndView, actualAddUserResult);
        assertTrue(actualAddUserResult.isReference());
        assertSame(actualAddUserResult.getModel(), actualAddUserResult.getModelMap());
        verify(userService).saveNewUser(any(), any());
    }

    @Test
    void testGetAllUsers() {
        when(userService.getAll()).thenReturn(new ArrayList<>());
        ModelAndView modelAndView = new ModelAndView("View Name");
        ModelAndView actualAllUsers = superController.getAllUsers(modelAndView);
        assertSame(modelAndView, actualAllUsers);
        assertTrue(actualAllUsers.isReference());
        assertSame(actualAllUsers.getModel(), actualAllUsers.getModelMap());
        verify(userService).getAll();
    }

}

