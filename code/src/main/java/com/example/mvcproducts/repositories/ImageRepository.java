package com.example.mvcproducts.repositories;

import com.example.mvcproducts.domain.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> getImagesByPlaylistId(Long playlistId);
}
