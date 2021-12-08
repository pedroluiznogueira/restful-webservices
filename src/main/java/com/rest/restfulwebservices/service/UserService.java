package com.rest.restfulwebservices.service;

import com.rest.restfulwebservices.model.User;
import com.rest.restfulwebservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id).get();
    }
}
