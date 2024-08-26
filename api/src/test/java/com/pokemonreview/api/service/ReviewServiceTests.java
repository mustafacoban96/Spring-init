package com.pokemonreview.api.service;


import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.ReviewDto;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.exceptions.ReviewNotFoundException;
import com.pokemonreview.api.model.Pokemon;
import com.pokemonreview.api.model.Review;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.repository.ReviewRepository;
import com.pokemonreview.api.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTests {

    private PokemonRepository pokemonRepository;
    private ReviewRepository reviewRepository;

    private ReviewServiceImpl reviewService;

    @BeforeEach
    void setUp(){
        pokemonRepository = Mockito.mock(PokemonRepository.class);
        reviewRepository = Mockito.mock(ReviewRepository.class);
        reviewService = new ReviewServiceImpl(reviewRepository,pokemonRepository);
    }

    @Test
    void shouldReturnReviewDto_whenCreateReview(){

        //given
        int id = 23;
        Pokemon pokemon = Pokemon.builder()
                .id(id)
                .name("aa")
                .type("tt")
                .build();
        ReviewDto reviewDto = ReviewDto.builder()
                .id(23)
                .title("tt")
                .content("cc")
                .stars(3)
                .pokemon(pokemon)
                .build();
        Review review = Review.builder()
                .id(23)
                .title("Great Pokemon")
                .content("Loved the battles")
                .stars(4)
                .pokemon(pokemon)
                .build();
        Review savedReview = Review.builder()
                .id(23)
                .title("Great Pokemon")
                .content("Loved the battles")
                .stars(4)
                .pokemon(pokemon)
                .build();

        //when
        when(pokemonRepository.findById(pokemon.getId())).thenReturn(Optional.of(pokemon));
        when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(savedReview);

        //then
        ReviewDto actual = reviewService.createReview(pokemon.getId(), reviewDto);

        Assertions.assertEquals(savedReview.getId(), actual.getId());
        Assertions.assertEquals(savedReview.getTitle(), actual.getTitle());
        Assertions.assertEquals(savedReview.getContent(), actual.getContent());
        Assertions.assertEquals(savedReview.getStars(), actual.getStars());
        Assertions.assertEquals(savedReview.getPokemon(), actual.getPokemon());
    }

    @Test
    void whenCreateReview_shouldReturnPokemonNotFoundException(){
        int id = 22;
        ReviewDto reviewDto = ReviewDto.builder().build();
        when(pokemonRepository.findById(id)).thenReturn(Optional.empty());

        org.junit.jupiter.api.Assertions.assertThrows(PokemonNotFoundException.class,
                ()->reviewService.createReview(id,reviewDto));

    }

    @Test
    void whenGetReviewByPokemonId_shouldReturnListReviewDto(){
        int id = 89;
        Pokemon pokemon = Pokemon.builder()
                .id(id)
                .name("w")
                .type("e")
                .build();
        Review review = Review.builder()
                .pokemon(pokemon)
                .title("t")
                .stars(2)
                .content("asd")
                .build();
        Review review2 = Review.builder()
                .pokemon(pokemon)
                .title("t")
                .stars(2)
                .content("asd")
                .build();
        //when
        when(reviewRepository.findByPokemonId(id)).thenReturn(List.of(review,review2));

        List<ReviewDto> rr = reviewService.getReviewsByPokemonId(id);
        org.assertj.core.api.Assertions.assertThat(rr).isNotNull();
        org.assertj.core.api.Assertions.assertThat(rr).hasSize(2);

    }

    @Test
    void shouldReturnReviewDto_whenGetReviewById(){
        int pokemonId = 3;
        int reviewId = 4;

        Pokemon pokemon = Pokemon.builder()
                .id(pokemonId)
                .build();
        Review review = Review.builder()
                .id(reviewId)
                .pokemon(pokemon)
                .build();


        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        ReviewDto reviewDto = reviewService.getReviewById(reviewId,pokemonId);

        org.assertj.core.api.Assertions.assertThat(reviewDto).isNotNull();
    }

    @Test
    void shouldReturnPokemonNotFoundException_whenGetReviewById(){
        int pokemonId = 2;
        int reviewId = 3;



        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.empty());

        Assertions.assertThrows(PokemonNotFoundException.class,() -> reviewService.getReviewById(reviewId,pokemonId));

    }

    @Test
    void shouldReturnReviewNotFoundException_whenGetReviewById(){
        int pokemonId = 2;
        int reviewId = 3;
        Pokemon pokemon = Pokemon.builder()
                .id(pokemonId)
                .build();



        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());
        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));
        Assertions.assertThrows(ReviewNotFoundException.class,() -> reviewService.getReviewById(reviewId,pokemonId));

    }

    @Test
    void shouldReturnReviewNotFoundException_whenReviewPokemonIdNotEqualPokemon() {
        // given
        int pokemonId = 2; // The ID that we will pass to the method
        int reviewId = 3; // The ID of the review we are looking for

        Pokemon notInReviewPokemon = Pokemon.builder().id(pokemonId).build();

        // Create a Pokémon with a different ID
        Pokemon pokemonInReview = Pokemon.builder()
                .id(244) // This ID is different from the one we will pass to the method
                .build();

        // Create a Review with the mismatched Pokémon
        Review review = Review.builder()
                .id(reviewId)
                .pokemon(pokemonInReview)
                .build();

        // Stub the repositories to return the review and the Pokémon
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(notInReviewPokemon)); // The Pokémon we expect

        // then
        Assertions.assertThrows(ReviewNotFoundException.class, () -> {
            reviewService.getReviewById(reviewId, pokemonId);
        });

        // Verify that the correct methods were called
        verify(reviewRepository).findById(reviewId);
        verify(pokemonRepository).findById(pokemonId);
    }

    @Test
    void shouldReviewDto_whenUpdateReview(){
        int pokemonId = 2;
        int reviewId = 3;

        Pokemon pokemon = Pokemon.builder().id(pokemonId).build();
        Review review = Review.builder()
                .id(reviewId)
                .stars(2)
                .pokemon(pokemon)
                .content("cc")
                .title("tt")
                .build();
        Review savedReview = Review.builder()
                .id(reviewId)
                .stars(2)
                .pokemon(pokemon)
                .content("cca")
                .title("tt")
                .build();
        ReviewDto expected = ReviewDto.builder()
                .id(reviewId)
                .stars(2)
                .pokemon(pokemon)
                .content("cca")
                .title("tt")
                .build();
        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        when(reviewRepository.save(Mockito.any(Review.class))).thenReturn(savedReview);

        ReviewDto actual = reviewService.updateReview(pokemonId,reviewId,expected);

        Assertions.assertEquals(expected,actual);

        verify(pokemonRepository).findById(pokemonId);
        verify(reviewRepository).findById(reviewId);
        verify(reviewRepository).save(Mockito.any(Review.class));
    }


    @Test
    void shouldReturnPokemonNotFoundException_whenUpdateReview(){
        int pokemonId =32;
        int reviewId = 44;

        ReviewDto reviewDto = ReviewDto.builder()
                .id(reviewId)
                .build();
        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.empty());

        Assertions.assertThrows(PokemonNotFoundException.class,() -> reviewService.updateReview(pokemonId,reviewId,reviewDto));

        verify(pokemonRepository,times(1)).findById(pokemonId);

    }

    @Test
    void shouldReturnReviewNotFoundException_whenUpdateReview(){
        int pokemonId =32;
        int reviewId = 44;
        Pokemon pokemon = Pokemon.builder().id(pokemonId).build();

        ReviewDto reviewDto = ReviewDto.builder()
                .id(reviewId)
                .build();
        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ReviewNotFoundException.class,() -> reviewService.updateReview(pokemonId,reviewId,reviewDto));

        verify(pokemonRepository,times(1)).findById(pokemonId);
        verify(reviewRepository,times(1)).findById(reviewId);


    }


    @Test
    void shouldReturnReviewNotFoundException_whenPokemonIdBelongsToReviewInUpdateReview(){
        int pokemonId = 2; // The ID that we will pass to the method
        int reviewId = 3; // The ID of the review we are looking for

        Pokemon notInReviewPokemon = Pokemon.builder().id(pokemonId).build();

        // Create a Pokémon with a different ID
        Pokemon pokemonInReview = Pokemon.builder()
                .id(244) // This ID is different from the one we will pass to the method
                .build();

        // Create a Review with the mismatched Pokémon
        Review review = Review.builder()
                .id(reviewId)
                .pokemon(pokemonInReview)
                .build();
        ReviewDto reviewDto = ReviewDto.builder()
                .id(reviewId)
                .build();

        // Stub the repositories to return the review and the Pokémon
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(notInReviewPokemon)); // The Pokémon we expect


        Assertions.assertThrows(ReviewNotFoundException.class,() -> reviewService.updateReview(pokemonId,reviewId,reviewDto));

        verify(pokemonRepository,times(1)).findById(pokemonId);
        verify(reviewRepository,times(1)).findById(reviewId);

    }

    @Test
    void shouldReturnPokemonNotFoundException_whenDeleteReview(){
        int pokemonId =32;
        int reviewId = 44;


        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.empty());

        Assertions.assertThrows(PokemonNotFoundException.class,() -> reviewService.deleteReview(pokemonId,reviewId));

        verify(pokemonRepository,times(1)).findById(pokemonId);
    }

    @Test
    void shouldReturnReviewNotFoundException_whenDeleteReview(){
        int pokemonId =32;
        int reviewId = 44;

        Pokemon pokemon = Pokemon.builder().id(pokemonId).build();

        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ReviewNotFoundException.class,() -> reviewService.deleteReview(pokemonId,reviewId));

        verify(pokemonRepository,times(1)).findById(pokemonId);
        verify(reviewRepository,times(1)).findById(reviewId);

    }

    @Test
    void shouldReturnReviewNotFoundException_whenPokemonIdBelongsToReviewInDeleteReview(){
        int pokemonId = 2; // The ID that we will pass to the method
        int reviewId = 3; // The ID of the review we are looking for

        Pokemon notInReviewPokemon = Pokemon.builder().id(pokemonId).build();

        // Create a Pokémon with a different ID
        Pokemon pokemonInReview = Pokemon.builder()
                .id(244) // This ID is different from the one we will pass to the method
                .build();

        // Create a Review with the mismatched Pokémon
        Review review = Review.builder()
                .id(reviewId)
                .pokemon(pokemonInReview)
                .build();


        // Stub the repositories to return the review and the Pokémon
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));
        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(notInReviewPokemon)); // The Pokémon we expect


        Assertions.assertThrows(ReviewNotFoundException.class,() -> reviewService.deleteReview(pokemonId,reviewId));

        verify(pokemonRepository,times(1)).findById(pokemonId);
        verify(reviewRepository,times(1)).findById(reviewId);
        verify(reviewRepository,times(0)).delete(review);

    }

    @Test
    void shouldDeleteReview_whenPokemonIdAndReviewId(){
        int pokemonId = 2;
        int reviewId = 3;

        Pokemon pokemon = Pokemon.builder().id(pokemonId).build();
        Review review = Review.builder().id(reviewId).pokemon(pokemon).build();

        when(pokemonRepository.findById(pokemonId)).thenReturn(Optional.of(pokemon));
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        reviewService.deleteReview(pokemonId,reviewId);

        verify(reviewRepository,times(1)).delete(review);
    }






    @AfterEach
    void tearDown(){
        // clear
    }


}
