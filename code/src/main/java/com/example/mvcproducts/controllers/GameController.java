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
    public static final String[] GAME_PHASE_1 = {"The MacMillan Estate", "Autohaven Wreckers", "Coldwind Farm", "Crotus Prenn Asylum", "Haddonfield", "Backwater Swamp", "Léry's Memorial Institute", "Red Forest", "Springwood", "Gideon Meat Plant", "Yamaoka Estate", "Ormond", "Grave of Glenvale", "Silent Hill", "Raccoon City", "Forsaken Boneyard", "Withered Isle", "The Decimated Borgo", "Dvarka Deepwood"};
    public static final String[] GAME_PHASE_2 = {"Coal Tower","Groaning Storehouse","Ironworks of Misery","Shelter Woods","Suffocation Pit","Azarov's Resting Place","Blood Lodge","Gas Heaven","Wreckers' Yard","Wretched Shop","Fractured Cowshed","Rancid Abattoir","Rotten Fields","The Thompson House","Torment Creek","Disturbed Ward","Father Campbell's Chapel","Lampkin Lane","The Pale Rose","Grim Pantry","Treatment Theatre","Mother's Dwelling","The Temple of Purgation","Badham Preschool I","Badham Preschool II","Badham Preschool III","Badham Preschool IV","Badham Preschool V","The Game","Family Residence","Sanctum of Wrath","Mount Ormond Resort","Dead Dawg Saloon","Midwich Elementary School","Raccoon City Police Station East Wing","Raccoon City Police Station West Wing","Eyrie of Crows","Garden of Joy","The Shattered Square","Toba Landing"};
    public static final String[] GAME_PHASE_3 = {"The Basement","The Killer Shack","Hill","Main Building","Pallet Gyms","Jungle Gyms","4-Wall Gyms","Debris Pile Gyms","Locker Gyms","Labyrinth Gyms","Variant Gyms"};

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
