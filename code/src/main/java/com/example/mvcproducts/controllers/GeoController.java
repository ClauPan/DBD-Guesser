package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.Playlist;
import com.example.mvcproducts.services.PlaylistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Controller
public class GeoController {
    public static final String[] GAME_PHASE_1 = {"map1", "map2", "map3"};
    public static final String[] GAME_PHASE_2 = {"var1", "var2", "var3"};
    public static final String[] GAME_PHASE_3 = {"str1", "str2", "str3"};

    public static final int MAX_WIDTH = 500;
    public static final int MAX_HEIGHT = 500;


    private final PlaylistService playlistService;

    public GeoController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }
    @GetMapping("/geo")
    public String Geo(Model model, @RequestParam("pid") Long pid){
        Playlist playlist = playlistService.getPlaylistById(pid);
        File[] images = new File(PlaylistController.PLAYLIST_DIR + "\\" + playlist.getUser().getId() + "_" + playlist.getId() + "_" + playlist.getName()).listFiles();
        model.addAttribute("images", images);
        return "geoGame";
    }
}
