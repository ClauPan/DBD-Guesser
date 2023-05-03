package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

  @GetMapping
  public String home(Authentication authentication) {
    if (authentication != null) {
      return "home";
    }
    else {
      return "homeUnsigned";
    }
  }
}