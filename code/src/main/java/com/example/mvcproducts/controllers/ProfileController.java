package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.services.UserService;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;




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
        PlaylistController.DELETE_TEMP = true;
        User user = (User) authentication.getPrincipal();
        model.addAttribute("profile", user);
        return "profileEdit";
    }

    @PostMapping("/profile/reset/pic")
    public String changeProfilePic(Authentication authentication, @RequestParam("img")MultipartFile file) throws IOException {
        User user = (User) authentication.getPrincipal();
        Path path = Paths.get(PROFILE_PIC_DIR + "\\" + user.getId().toString() + "_" + user.getUsername() + "\\profile.png");
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        BufferedImage resizedImage = Thumbnails.of(file.getInputStream()).size(100, 100).asBufferedImage();
        ImageIO.write(resizedImage, "png", path.toFile());
        return "redirect:/profile";
    }

    @GetMapping("/images/profiles/{id}_{username}/profile.png")
    public ResponseEntity<byte[]> getProfilePic(@PathVariable Long id, @PathVariable String username) throws IOException {
        Path path = Paths.get(PROFILE_PIC_DIR + "\\" + id + "_" + username + "\\profile.png");
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity<>(Files.readAllBytes(path), headers, HttpStatus.OK);
    }

    @PostMapping("/profile/reset/password")
    public String changePassword(Authentication authentication, @RequestParam("old_pass") String oldPass, @RequestParam("new_pass") String newPass) {
        PasswordEncoder bcrypt = new BCryptPasswordEncoder();
        User user = (User) authentication.getPrincipal();
        if (bcrypt.matches(oldPass, user.getPassword())) {
            user.setPassword(bcrypt.encode(newPass));
        }
        userService.save(user);
        return "redirect:/profile";
    }

    @PostMapping("/profile/reset/email")
    public String changeEmail(Authentication authentication, @RequestParam("old_email") String oldEmail, @RequestParam("new_email") String newEmail) {
        User user = (User) authentication.getPrincipal();
        if (user.getEmail().equals(oldEmail)) {
            user.setEmail(newEmail);
        }
        userService.save(user);
        return "redirect:/profile";
    }
}
