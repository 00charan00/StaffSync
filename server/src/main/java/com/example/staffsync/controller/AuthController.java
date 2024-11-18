package com.example.staffsync.controller;

import com.example.staffsync.model.User;
import com.example.staffsync.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // Matches "register.html" in templates
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        String message = authService.registerUser(user);
        model.addAttribute("message", message);

        // Redirect if successful registration, otherwise stay on the register page
        if (message.equals("User registered successfully!")) {
            return "redirect:/auth/login";
        }
        return "register";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new User());
        return "login"; // Matches "login.html" in templates
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model) {
        String message = authService.loginUser(user);
        model.addAttribute("message", message);

        if (message.equals("Login successful!")) {
            return "home"; // Matches "home.html" in templates
        }
        return "login";
    }
}
