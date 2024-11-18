package com.example.staffsync.service;

import com.example.staffsync.model.User;
import com.example.staffsync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return "Email already registered!";
        }
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return "Username already taken!";
        }
        userRepository.save(user);
        return "User registered successfully!";
    }

    public String loginUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            existingUser = userRepository.findByUsername(user.getUsername());
        }
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "Login successful!";
        }
        return "Invalid username/email or password!";
    }
}
