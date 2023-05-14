package com.example.mvcproducts.controllers;

import com.example.mvcproducts.services.GeoGameService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class GeoController {
    public static final String[] GAME_PHASE_1 = {"map1", "map2", "map3"};
    public static final String[] GAME_PHASE_2 = {"var1", "var2", "var3", "Can't Tell"};
    public static final String[] GAME_PHASE_3 = {"str1", "str2", "str3"};

    public static final int MAX_WIDTH = 500;
    public static final int MAX_HEIGHT = 500;


    private final GeoGameService geoGameService;

    public GeoController(GeoGameService geoGameService) {
        this.geoGameService = geoGameService;
    }
    @GetMapping("/geo")
    public String Geo(){
        return "geoGame";
    }
}
