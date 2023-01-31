package com.spring_data_new.service;

import com.spring_data_new.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User saveUser(User user);
}
