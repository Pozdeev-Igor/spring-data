package com.spring_data.service;

import com.spring_data.model.User;
import com.spring_data.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }


    public User saveNewUser(String firstName, String lastName) {
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        return repo.save(newUser);
    }


    public List<User> getAll() {
        return repo.findAll();
    }
}
