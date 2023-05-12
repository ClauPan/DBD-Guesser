package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.UserData;
import com.example.mvcproducts.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/auth/login")
    public String login() {
        return "login";
    }
    @RequestMapping("/auth/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
    @GetMapping("/auth/register")
    public String register(final Model model){
        model.addAttribute("userData", new UserData());
        return "register";
    }
    @PostMapping("/auth/register")
    public String userRegistration(UserData userData){
        userData.encode();
        userService.save(userData);
        return "home";
    }
}
