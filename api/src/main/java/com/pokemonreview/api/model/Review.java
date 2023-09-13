package com.pokemonreview.api.model;


import lombok.Data;

@Data
public class Review {

    private int id;
    private String title;
    private String content;
    private int start;
}
