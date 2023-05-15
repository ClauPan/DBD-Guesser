package com.example.mvcproducts.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class PlaylistData {
    private String name;
    private String type;
    private String description;
    private User user = null;
    private List<String> images = new ArrayList<>();

    public void addImage(String image) {
        this.images.add(image);
    }
}
