package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Image;
import com.example.mvcproducts.domain.Playlist;
import com.example.mvcproducts.domain.Rating;

import java.util.List;

public interface PlaylistService {
    void save(Playlist playlist);
    List<Playlist> getPlaylistsByType(String type);
    Playlist getPlaylistById(Long id);
    List<Rating> getRatingsByPlaylistId(Long playlistId);
    List<Image> getImagesByPlaylistId(Long playlistId);

    double getOverallRating(Long playlistId);
}
