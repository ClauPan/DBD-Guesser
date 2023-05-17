package com.example.mvcproducts.controllers;

import com.example.mvcproducts.services.PlaylistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TriviaController {
    @GetMapping("/triviaGame")
    public String trivia(Model model, @RequestParam("type") String type) {
        model.addAttribute("name", type);
        return "triviaGame";
    }
}
