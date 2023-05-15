package com.example.mvcproducts.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Playlist {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String type;

    private String description;

    @OneToOne
    private User user;

    public Playlist(String name, String type, String desc, User user) {
        this.name = name;
        this.type = type;
        this.description = desc;
        this.user = user;
    }

    public Playlist(PlaylistData playlistData) {
        this.name = playlistData.getName();
        this.description = playlistData.getDescription();
        this.type = playlistData.getType();
        this.user = playlistData.getUser();
    }
}
