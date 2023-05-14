package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.EntryData;
import com.example.mvcproducts.domain.PlaylistData;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.services.PlaylistService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Controller
@SessionAttributes("playlist")
public class PlaylistController {
    private final String PLAYLIST_DIR = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\playlists";
    private Path TEMP_DIR = null;

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @ModelAttribute("playlist")
    public PlaylistData playlist() {
        return new PlaylistData();
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

    @GetMapping("/playlist/create")
    public String createPlaylist_start(Authentication authentication, Model model, @ModelAttribute("playlist") PlaylistData playlistData) throws IOException {
        User user = (User) authentication.getPrincipal();
        if (TEMP_DIR == null) {
            TEMP_DIR = Paths.get(PLAYLIST_DIR + "\\temp\\" + user.getId() + "_" + user.getUsername());
        }
        if (!Files.exists(TEMP_DIR)) {
            Files.createDirectories(TEMP_DIR);
        }
        playlistData.setUser(user);
        return "playlistCreate";
    }

    @GetMapping("/images/playlists/temp/{id}_{username}/{filename}")
    public ResponseEntity<byte[]> getProfilePic(@PathVariable String filename) throws IOException {
        Path path = Paths.get(TEMP_DIR + "\\" + filename);
        HttpHeaders headers = new HttpHeaders();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());
        return new ResponseEntity<>(Files.readAllBytes(path), headers, HttpStatus.OK);
    }

    @GetMapping("/playlist/create/add")
    public String createPlaylist_addImage(Model model) {
        model.addAttribute("GAME_PHASE_1", GeoController.GAME_PHASE_1);
        model.addAttribute("GAME_PHASE_2", GeoController.GAME_PHASE_2);
        model.addAttribute("GAME_PHASE_3", GeoController.GAME_PHASE_3);
        return "playlistCreateAdd";
    }

    @PostMapping("/playlist/create/add")
    public String createPlaylist_addImage_save(@RequestParam("img") MultipartFile file, @RequestParam("choice_1") String choice_1,
                                               @RequestParam("choice_2") String choice_2, @RequestParam("choice_3") String choice_3,
                                               @ModelAttribute("playlist") PlaylistData playlistData) throws IOException {

        String filename = playlistData.getImages().size() + "_" + choice_1 + "_" + choice_2 + "_" + choice_3 + ".png";

        playlistData.getImages().add(new EntryData(filename, playlistData.getImages().size()));

        BufferedImage resizedImage = Thumbnails.of(file.getInputStream()).size(GeoController.MAX_WIDTH, GeoController.MAX_HEIGHT).asBufferedImage();
        ImageIO.write(resizedImage, "png", Paths.get(TEMP_DIR.toString() + "\\" + filename).toFile());
        return "redirect:/playlist/create";
    }
}
