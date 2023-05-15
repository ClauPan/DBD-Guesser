package com.example.mvcproducts.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;

@Data
@NoArgsConstructor
public class EntryData {
    private String filename;
    public EntryData(String filename) {
        this.filename = filename;
    }
}
