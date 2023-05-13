package com.example.mvcproducts.repositories;

import com.example.mvcproducts.domain.Rating;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingRepository extends CrudRepository<Rating, Long> {
    List<Rating> getRatingsByPlaylistId(Long playlistId);
}
