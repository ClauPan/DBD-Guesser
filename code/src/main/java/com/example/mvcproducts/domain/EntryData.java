package com.example.mvcproducts.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;

@Data
@NoArgsConstructor
public class EntryData {
    private String filename;
    private int index;
    public EntryData(String filename, int index) {
        this.filename = filename;
        this.index = index;
    }
}
