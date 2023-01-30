package com.spring_data.controller;

import com.spring_data.model.User;
import com.spring_data.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SuperController {

    private final UserService service;

    public SuperController(UserService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public ModelAndView hello(ModelAndView model) {
        model.setViewName("hello");
        model.addObject(new User());
        return model;
    }

    @PostMapping("/add-user")
    @ResponseBody
    public ModelAndView addUser(    @RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName,
                                    ModelAndView model) {
        User user = service.saveNewUser(firstName, lastName);
        model.setViewName("result");
        model.addObject(user);
        return model;
    }

    @GetMapping("/getall")
    public ModelAndView getAllUsers(ModelAndView model) {
        model.setViewName("allUsers");
        model.addObject(service.getAll());
        return model;
    }
}
