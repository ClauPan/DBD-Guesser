package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Playlist;
import com.example.mvcproducts.domain.Rating;
import com.example.mvcproducts.domain.UserPlaylistPlayed;
import com.example.mvcproducts.repositories.PlaylistRepository;
import com.example.mvcproducts.repositories.RatingRepository;
import com.example.mvcproducts.repositories.UserPlaylistPlayedRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService{
    private final PlaylistRepository playlistRepository;
    private final RatingRepository ratingRepository;
    private final UserPlaylistPlayedRepository playedRepository;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository, RatingRepository ratingRepository, UserPlaylistPlayedRepository playedRepository) {
        this.playlistRepository = playlistRepository;
        this.ratingRepository = ratingRepository;
        this.playedRepository = playedRepository;
    }

    public void save(Playlist playlist) {
        playlistRepository.save(playlist);
    }
    public void save(UserPlaylistPlayed played) {
        playedRepository.save(played);
    }
    @Override
    public List<Playlist> getPlaylistsByType(String type) {
        List<Playlist> playlists = playlistRepository.getPlaylistsByType(type);
        for (Playlist playlist : playlists) {
            playlist.setTotalRating(this.getOverallRating(playlist.getId()));
        }
        return playlists;
    }
    @Override
    public Playlist getPlaylistById(Long id) {
        Playlist playlist = playlistRepository.getPlaylistById(id);
        playlist.setTotalRating(this.getOverallRating(playlist.getId()));
        return playlist;
    }
    @Override
    public List<Rating> getRatingsByPlaylistId(Long playlistId) {
        return ratingRepository.getRatingsByPlaylistId(playlistId);
    }
    @Override
    public double getOverallRating(Long playlistId) {
        List<Rating> ratings = this.getRatingsByPlaylistId(playlistId);
        if (!ratings.isEmpty()) {
            double ratingScore = 0;
            for (Rating rating : ratings) {
                ratingScore += rating.getValue();
            }
            return ratingScore/ratings.size();
        }
        else {
            return 0;
        }
    }

    public UserPlaylistPlayed getUserPlaylistPlayedByUserIdAndPlaylistId(Long uid, Long pid) {
        return playedRepository.getUserPlaylistPlayedByUserIdAndPlaylistId(uid, pid);
    }
}
