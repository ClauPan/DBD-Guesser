package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Playlist;

import java.util.List;

public interface PlaylistService {
    void save(Playlist playlist);
    List<Playlist> getPlaylistsByType(String type);
    Playlist getPlaylistById(Long id);
}
