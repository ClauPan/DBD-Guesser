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

    private String thumbnail;

    private double rating;

    @OneToOne
    private User user;

    public Playlist(String name, String type, String desc, String thumbnail, User user) {
        this.name = name;
        this.type = type;
        this.description = desc;
        this.thumbnail = thumbnail;
        this.user = user;
        this.rating = 0.0;
    }
}
