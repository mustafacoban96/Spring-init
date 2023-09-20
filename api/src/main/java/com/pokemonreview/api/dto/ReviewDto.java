package com.pokemonreview.api.dto;

import com.pokemonreview.api.model.Pokemon;

import lombok.Data;


@Data
public class ReviewDto {
	
	private int id;
	private String title;
	private String content;
	private int stars;
	private Pokemon pokemon;
}
