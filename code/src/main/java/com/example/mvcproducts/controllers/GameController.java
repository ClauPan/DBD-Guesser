package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.Playlist;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.domain.UserPlaylistPlayed;
import com.example.mvcproducts.services.PlaylistService;
import com.example.mvcproducts.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.ArrayList;

@Controller
public class GameController {
    public static final String[] GAME_PHASE_1 = {"map1", "map2", "map3"};
    public static final String[] GAME_PHASE_2 = {"var1", "var2", "var3"};
    public static final String[] GAME_PHASE_3 = {"str1", "str2", "str3"};

    public static final int MAX_WIDTH = 500;
    public static final int MAX_HEIGHT = 500;


    private final PlaylistService playlistService;
    private final UserService userService;

    public GameController(PlaylistService playlistService, UserService userService) {
        this.playlistService = playlistService;
        this.userService = userService;
    }
    @GetMapping("/geo")
    public String geoGame(Model model, @RequestParam("pid") Long pid){
        Playlist playlist = playlistService.getPlaylistById(pid);
        String path = playlist.getUser().getId() + "_" + playlist.getId() + "_" + playlist.getName();
        File[] images = new File(PlaylistController.PLAYLIST_DIR + "\\" + path).listFiles();

        ArrayList<String> filenames = new ArrayList<>();
        if (images != null) {
            for (File img : images) {
                filenames.add(img.getName());
            }
        }
        model.addAttribute("pid", pid);
        model.addAttribute("path", path);
        model.addAttribute("images", filenames);
        model.addAttribute("choice_1", GAME_PHASE_1);
        model.addAttribute("choice_2", GAME_PHASE_2);
        model.addAttribute("choice_3", GAME_PHASE_3);
        return "geoGame";
    }

    @RequestMapping("/geo/end")
    public String geoEnd(Authentication authentication, @RequestParam("score") String score, @RequestParam("pid") Long pid) {
        User user = (User) authentication.getPrincipal();
        if (playlistService.getUserPlaylistPlayedByUserIdAndPlaylistId(user.getId(), pid) != null) {
            UserPlaylistPlayed played = new UserPlaylistPlayed(user, playlistService.getPlaylistById(pid));
            playlistService.save(played);
            user.setScore(user.getScore() + Integer.parseInt(score));
            userService.save(user);
        }
        return "redirect:/";
    }

    @GetMapping("/trivia")
    public String trivia(Model model, @RequestParam("type") String type) {
        model.addAttribute("name", type);
        return "triviaGame";
    }
}
