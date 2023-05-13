package com.example.mvcproducts.services;

import com.example.mvcproducts.domain.Image;
import com.example.mvcproducts.domain.Playlist;
import com.example.mvcproducts.domain.Rating;
import com.example.mvcproducts.repositories.ImageRepository;
import com.example.mvcproducts.repositories.PlaylistRepository;
import com.example.mvcproducts.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService{
    private final PlaylistRepository playlistRepository;
    private final RatingRepository ratingRepository;
    private final ImageRepository imageRepository;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository, RatingRepository ratingRepository, ImageRepository imageRepository) {
        this.playlistRepository = playlistRepository;
        this.ratingRepository = ratingRepository;
        this.imageRepository = imageRepository;
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
    public List<Image> getImagesByPlaylistId(Long playlistId) {
        return imageRepository.getImagesByPlaylistId(playlistId);
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
