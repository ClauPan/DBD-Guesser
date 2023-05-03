package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Playlist;
import com.example.mvcproducts.repositories.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService{
    private final PlaylistRepository playlistRepository;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public void save(Playlist playlist) {
        playlistRepository.save(playlist);
    }

    @Override
    public List<Playlist> getPlaylistsByType(String type) {
        return playlistRepository.getPlaylistsByType(type);
    }

    @Override
    public Playlist getPlaylistById(Long id) {
        return playlistRepository.getPlaylistById(id);
    }
}
