package com.rest.restfulwebservices.controller;

import com.rest.restfulwebservices.exception.UserNotFoundException;
import com.rest.restfulwebservices.model.User;
import com.rest.restfulwebservices.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getById/{id}")
    public EntityModel<Optional<User>> getById(@PathVariable ("id") Long id) {
        Optional<User> user = userService.getById(id);
        if (user.isEmpty()) throw new UserNotFoundException("id-" + id);

        EntityModel model =  EntityModel.of(user);
        WebMvcLinkBuilder linkToUsers = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsers());
        model.add(linkToUsers.withRel("get all users"));

        return model;
    }

    @GetMapping("getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        User userResp = userService.registerUser(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userResp.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable ("id") Long id) {
        User user = userService.deleteUser(id);
        if (user == null) return ResponseEntity.noContent().build();

        return ResponseEntity.ok(user);
    }
}
