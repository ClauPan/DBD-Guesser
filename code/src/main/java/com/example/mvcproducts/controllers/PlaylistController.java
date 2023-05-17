package com.example.mvcproducts.controllers;

import com.example.mvcproducts.domain.Playlist;
import com.example.mvcproducts.domain.PlaylistData;
import com.example.mvcproducts.domain.User;
import com.example.mvcproducts.services.PlaylistService;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tomcat.util.http.fileupload.FileUtils;
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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@SessionAttributes("playlist")
public class PlaylistController {

    public static final String PLAYLIST_DIR = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\playlists";
    private Path TEMP_DIR = null;
    public static boolean DELETE_TEMP = true;
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
        DELETE_TEMP = true;
        model.addAttribute("playlists", playlistService.getPlaylistsByType(ptype));
        if (ptype.equals("geo")) {
            return "/playlist/listGeo";
        }
        else {
            return "/playlist/listTrivia";
        }
    }

    @GetMapping("/playlists/entry")
    public String showPlaylist(Model model, @RequestParam Long pid) {
        DELETE_TEMP = true;
        model.addAttribute("playlistEntry", playlistService.getPlaylistById(pid));
        model.addAttribute("rating", playlistService.getOverallRating(pid));
        return "playlist/entry";
    }

    @GetMapping("/playlist/create")
    public String createPlaylist_start(Authentication authentication, @ModelAttribute("playlist") PlaylistData playlistData) throws IOException {
        User user = (User) authentication.getPrincipal();
        if (TEMP_DIR == null) {
            TEMP_DIR = Paths.get(PLAYLIST_DIR + "\\temp\\" + user.getId() + "_" + user.getUsername());
        }
        if (DELETE_TEMP) {
            FileUtils.deleteDirectory(new File(TEMP_DIR.toString()));
            playlistData.getImages().clear();
            DELETE_TEMP = false;
        }
        if (!Files.exists(TEMP_DIR)) {
            Files.createDirectories(TEMP_DIR);
        }
        if (playlistData.getUser() == null) {
            playlistData.setUser(user);
        }
        return "playlist/createMain";
    }

    @GetMapping("/images/playlists/temp/*/{filename}")
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
        return "playlist/createAdd";
    }

    @PostMapping("/playlist/create/add")
    public String createPlaylist_addImage_save(@RequestParam("img") MultipartFile file, @RequestParam("choice_1") String choice_1,
                                               @RequestParam("choice_2") String choice_2, @RequestParam("choice_3") String choice_3,
                                               @ModelAttribute("playlist") PlaylistData playlistData) throws IOException {

        String filename = playlistData.getImages().size() + "_" + choice_1 + "_" + choice_2 + "_" + choice_3 + ".png";

        BufferedImage resizedImage = Thumbnails.of(file.getInputStream()).size(GeoController.MAX_WIDTH, GeoController.MAX_HEIGHT).asBufferedImage();
        ImageIO.write(resizedImage, "png", Paths.get(TEMP_DIR.toString() + "\\" + filename).toFile());

        playlistData.addImage(filename);

        return "redirect:/playlist/create";
    }

    @GetMapping("/playlist/create/edit")
    public String createPlaylist_editImage(Model model, @RequestParam("index") String index) {
        model.addAttribute("index", index);
        model.addAttribute("GAME_PHASE_1", GeoController.GAME_PHASE_1);
        model.addAttribute("GAME_PHASE_2", GeoController.GAME_PHASE_2);
        model.addAttribute("GAME_PHASE_3", GeoController.GAME_PHASE_3);
        return "/playlist/createEdit";
    }

    @PostMapping("/playlist/create/edit")
    public String createPlaylist_editImage_save(@RequestParam("index") String index, @RequestParam("choice_1") String choice_1,
                                                @RequestParam("choice_2") String choice_2, @RequestParam("choice_3") String choice_3,
                                                @ModelAttribute("playlist") PlaylistData playlistData) throws IOException {

        String oldFilename = playlistData.getImages().get(Integer.parseInt(index));
        String newFilename = index + "_" + choice_1 + "_" + choice_2 + "_" + choice_3 + ".png";

        playlistData.getImages().set(Integer.parseInt(index), newFilename);

        Files.move(Paths.get(TEMP_DIR.toString() + "\\" + oldFilename), Paths.get(TEMP_DIR.toString() + "\\" + newFilename), StandardCopyOption.REPLACE_EXISTING);
        return "redirect:/playlist/create";
    }

    @PostMapping("/playlist/create/delete")
    public String createPlaylist_deleteImage(@RequestParam("index") String index, @ModelAttribute("playlist") PlaylistData playlistData) throws IOException {
        String filename = playlistData.getImages().remove(Integer.parseInt(index));
        Files.delete(Paths.get(TEMP_DIR.toString() + "\\" + filename));
        return "redirect:/playlist/create";
    }

    @PostMapping("/playlist/create/finish")
    public String createPlaylist_save(@RequestParam("name") String name, @RequestParam("desc") String desc,
                                      @RequestParam("type") String type, @ModelAttribute("playlist") PlaylistData playlistData) throws IOException {

        playlistData.setName(name);
        playlistData.setDescription(desc);
        playlistData.setType(type);

        Playlist playlist = new Playlist(playlistData);
        playlistService.save(playlist);

        Path playlistPath = Paths.get(PLAYLIST_DIR + "\\" + playlist.getUser().getId() + "_" + playlist.getId() + "_" + playlist.getName());
        if (!Files.exists(playlistPath)) {
            Files.createDirectories(playlistPath);
        }

        File[] images = TEMP_DIR.toFile().listFiles();
        if (images != null) {
            boolean once = true;
            for (File image : images) {
                String filename = image.getName();
                if (once) {
                    Files.copy(Paths.get(image.getAbsolutePath()), Paths.get(playlistPath + "\\" + "thumb.png"), StandardCopyOption.REPLACE_EXISTING);
                    once = false;
                }
                Files.move(Paths.get(image.getAbsolutePath()), Paths.get(playlistPath + "\\" + filename), StandardCopyOption.REPLACE_EXISTING);
            }
        }


        return "redirect:/";
    }

}
