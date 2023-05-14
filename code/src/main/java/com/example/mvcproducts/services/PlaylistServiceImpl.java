package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Playlist;
import com.example.mvcproducts.domain.Rating;
import com.example.mvcproducts.repositories.PlaylistRepository;
import com.example.mvcproducts.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService{
    private final PlaylistRepository playlistRepository;
    private final RatingRepository ratingRepository;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository, RatingRepository ratingRepository) {
        this.playlistRepository = playlistRepository;
        this.ratingRepository = ratingRepository;
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
    @Override
    public List<Rating> getRatingsByPlaylistId(Long playlistId) {
        return ratingRepository.getRatingsByPlaylistId(playlistId);
    }
    @Override
    public double getOverallRating(Long playlistId) {
        List<Rating> ratings = this.getRatingsByPlaylistId(playlistId);
        if (ratings.size() > 0) {
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
}
