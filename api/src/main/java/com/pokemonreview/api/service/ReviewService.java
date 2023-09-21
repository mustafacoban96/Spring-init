package com.pokemonreview.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pokemonreview.api.dto.ReviewDto;



public interface ReviewService {
	
	
	ReviewDto createReview(int pokemonId,ReviewDto reviewDto);
	List<ReviewDto> getReviewsByPokemonId(int id);
	ReviewDto getReviewById(int reviewId, int pokemonId);
	ReviewDto updateReview(int pokemonId,int reviewId,ReviewDto reviewDto);
}
