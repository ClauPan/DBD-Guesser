package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ProfileController {
    private final String PROFILE_PIC_DIR = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\profiles";
    private final UserService userService;
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile/_")
    public String profileRedirect() {
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String showUserProfile(Authentication authentication, Model model) {
        User user = (User) authentication.getPrincipal();
        model.addAttribute("profile", user);
        return "profile";
    }

    @PostMapping("/profile/pic")
    public String changeProfilePic(Authentication authentication, @RequestParam("img") file) throws IOException {
        User user = (User) authentication.getPrincipal();
        System.out.println(PROFILE_PIC_DIR + "\\" + user.getId().toString() + "\\" + file.getOriginalFilename());

        return "redirect:/profile";
    }
}
