package com.rest.restfulwebservices.controller;

import com.rest.restfulwebservices.model.User;
import com.rest.restfulwebservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("register")
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return "User registered";
    }
}
