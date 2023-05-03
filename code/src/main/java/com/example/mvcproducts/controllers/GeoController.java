package com.example.mvcproducts.controllers;

import com.example.mvcproducts.services.GeoGameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeoController {

    private final GeoGameService geoGameService;

    public GeoController(GeoGameService geoGameService) {
        this.geoGameService = geoGameService;
    }
    @GetMapping("/geo")
    public String Geo(){
        return "geoGame";
    }
}
