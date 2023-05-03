package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.UserData;
import com.example.mvcproducts.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(final Model model){
        model.addAttribute("userData", new UserData());
        return "/register";
    }

    @PostMapping("/register")
    public String userRegistration(UserData userData){
        userData.encode();
        userService.save(userData);
        return "/home";
    }
}