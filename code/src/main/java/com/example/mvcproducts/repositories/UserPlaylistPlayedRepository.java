package com.example.mvcproducts.repositories;

import com.example.mvcproducts.domain.UserPlaylistPlayed;
import org.springframework.data.repository.CrudRepository;

public interface UserPlaylistPlayedRepository extends CrudRepository<UserPlaylistPlayed, Long> {
    UserPlaylistPlayed getUserPlaylistPlayedByUserIdAndPlaylistId(Long uid, Long pid);
}
