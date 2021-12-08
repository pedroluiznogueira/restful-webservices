package com.rest.restfulwebservices.controller;

import com.rest.restfulwebservices.model.User;
import com.rest.restfulwebservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getById/{id}")
    public User getById(@PathVariable ("id") Long id) {
        return userService.getById(id);
    }

    @GetMapping("getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User userResp = userService.registerUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userResp.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
