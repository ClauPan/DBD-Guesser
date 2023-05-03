package com.example.mvcproducts.repositories;

import com.example.mvcproducts.domain.Playlist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlaylistRepository extends CrudRepository<Playlist, Long> {
    List<Playlist> getPlaylistsByType(String type);
    Playlist getPlaylistById(Long id);
}
