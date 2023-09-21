package com.pokemonreview.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.service.ReviewService;

@RestController
@RequestMapping("/api/")
public class ReviewController {
	
	private ReviewService reviewService;

	@Autowired
	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
	}
	
	@PostMapping("pokemon/{pokemonId}/review")
	public ResponseEntity<ReviewDto> createReview(@PathVariable(value="pokemonId") int pokemonId ,@RequestBody ReviewDto reviewDto){
		return new ResponseEntity<>(reviewService.createReview(pokemonId, reviewDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/pokemon/{pokemonId}/reviews")
	public List<ReviewDto> getReviewsByPokemonId(@PathVariable(value="pokemonId") int pokemonId){
		return reviewService.getReviewsByPokemonId(pokemonId);
	}
	
	@GetMapping("pokemon/{pokemonId}/review/{id}")
	public ResponseEntity<ReviewDto> getReviewDetail(@PathVariable(value="id") int reviewId, @PathVariable(value="pokemonId") int pokemonId){
		
		return new ResponseEntity<ReviewDto>(reviewService.getReviewById(reviewId, pokemonId),HttpStatus.OK);
	}
	
	@PutMapping("/pokemon/{pokemonId}/reviews/{id}")
	public ResponseEntity<ReviewDto> updateReview(@PathVariable(value="pokemonId") int pokemonId,@PathVariable(value="id") int reviewId, @RequestBody ReviewDto reviewDto){
		ReviewDto updatedReviewDto = reviewService.updateReview(pokemonId, reviewId, reviewDto);
		return new ResponseEntity<>(updatedReviewDto,HttpStatus.OK);
		
	}

}
