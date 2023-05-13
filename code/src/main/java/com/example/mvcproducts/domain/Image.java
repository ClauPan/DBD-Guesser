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
public class Image {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Playlist playlist;
}
