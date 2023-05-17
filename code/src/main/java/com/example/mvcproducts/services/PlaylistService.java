package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Playlist;
import com.example.mvcproducts.domain.Rating;
import com.example.mvcproducts.domain.UserPlaylistPlayed;

import java.util.List;

public interface PlaylistService {
    void save(Playlist playlist);

    void save(UserPlaylistPlayed played);
    List<Playlist> getPlaylistsByType(String type);
    Playlist getPlaylistById(Long id);
    List<Rating> getRatingsByPlaylistId(Long playlistId);

    double getOverallRating(Long playlistId);
    public UserPlaylistPlayed getUserPlaylistPlayedByUserIdAndPlaylistId(Long uid, Long pid);
}
