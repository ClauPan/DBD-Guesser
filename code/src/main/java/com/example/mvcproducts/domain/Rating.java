package com.example.mvcproducts.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private double value;

    @OneToOne
    private Playlist playlist;

    @OneToOne
    private User user;
}
