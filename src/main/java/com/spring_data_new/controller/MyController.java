package com.spring_data_new.controller;

import com.spring_data_new.model.User;
import com.spring_data_new.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MyController {
    private final UserService service;

    public MyController(UserService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute(new User());
        return "hell";
    }

    @PostMapping("/add-user")
    public String addUser(Model model, User user) {
        model.addAttribute("user", service.saveUser(user));
        return "result";
    }

    @GetMapping("/getall")
    public String getAllUsers(Model model) {
        if (!service.findAll().isEmpty()) {
            model.addAttribute("userList", service.findAll());
            return "allUsers";
        }else return "noUsers";
    }
}
