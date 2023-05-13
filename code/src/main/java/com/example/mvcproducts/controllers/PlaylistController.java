package com.example.mvcproducts.controllers;

import com.example.mvcproducts.services.PlaylistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlaylistController {
    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/playlists")
    public String getPlaylists(Model model, @RequestParam String ptype) {
        model.addAttribute("playlists", playlistService.getPlaylistsByType(ptype));
        return "playlists";
    }

    @GetMapping("/playlists/entry")
    public String showPlaylist(Model model, @RequestParam Long pid) {
        System.out.println(pid);
        model.addAttribute("playlist", playlistService.getPlaylistById(pid));
        model.addAttribute("rating", playlistService.getOverallRating(pid));
        return "playlist";
    }
}
