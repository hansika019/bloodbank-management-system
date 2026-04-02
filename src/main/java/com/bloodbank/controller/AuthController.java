package com.bloodbank.controller;

import com.bloodbank.entity.User;
import com.bloodbank.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")

public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // ✅ REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        // check if username already exists
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser.isPresent()) {
            return "USERNAME_EXISTS";
        }

        userRepository.save(user);
        return "REGISTER_SUCCESS";
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public String login(@RequestBody User auth) {

        Optional<User> userOpt = userRepository.findByUsername(auth.getUsername());

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (user.getPassword().equals(auth.getPassword())) {
                return "SUCCESS";
            }
        }

        return "FAIL";
    }
}
