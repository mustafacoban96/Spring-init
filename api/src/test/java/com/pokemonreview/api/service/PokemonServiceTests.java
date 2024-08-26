package com.pokemonreview.api.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.model.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.service.impl.PokemonServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceTests {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonServiceImpl pokemonService;


    private ObjectMapper mapper = new ObjectMapper();
    @Test
    void PokemonService_CreatePokemon_ReturnsPokemonDto(){
        Pokemon pokemon = Pokemon.builder()
                .name("emir")
                .type("electric").build();

        PokemonDto pokemonDto = PokemonDto.builder().name("emir").type("electric").build();
        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon);
        PokemonDto savedPokemon = pokemonService.createPoekmon(pokemonDto);

        Assertions.assertThat(savedPokemon).isNotNull();

    }

    @Test
    public void PokemonService_GetAllPokemon_ReturnsResponseDto(){
        Page<Pokemon> pokemons = Mockito.mock(Page.class);

        when(pokemonRepository.findAll(Mockito.any(Pageable.class))).thenReturn(pokemons);

        //Act
        PokemonResponse savePokemon = pokemonService.getAllPokemon(1,10);
        //Assert
        Assertions.assertThat(savePokemon).isNotNull();

    }

    @Test
    void shouldReturnPokemonDto_WhenFindById(){
        //Given
        int id = 222;
        Pokemon pokemon = Pokemon.builder()
                .id(id)
                .name("aaaa")
                .type("qqq")
                .build();
        pokemonRepository.save(pokemon);
        //when
        //Mockladığın yere debug at ve evaluate et. hata alırrsan
        when(pokemonRepository.findById(id)).thenReturn(Optional.ofNullable(pokemon));

        //then
        PokemonDto pokemonReturn = pokemonService.getPokemonDto(id);
        Assertions.assertThat(pokemonReturn).isNotNull();
    }

    @Test
    void shouldReturnPokemonDto_whenUpdatePokemon(){
        int id = 121;
        //given
        Pokemon pokemon = Pokemon.builder()
                .id(id)
                .name("Lale")
                .type("flower")
                .build();


        PokemonDto pokemonDto = PokemonDto.builder()
                .id(id)
                .name("papatya")
                .type("fal")
                .build();


        //when
        when(pokemonRepository.findById(id)).thenReturn(Optional.of(pokemon));
        when(pokemonRepository.save(Mockito.any(Pokemon.class))).thenReturn(pokemon);

        PokemonDto updatedPokemon = pokemonService.updatePokemon(pokemonDto,id);

        // then
        org.junit.jupiter.api.Assertions.assertEquals(pokemonDto,updatedPokemon);
    }

    @Test
    void shouldThrowPokemonNotFoundException_whenUpdatePokemon(){
        int id = 2;
        PokemonDto pokemonDto = PokemonDto.builder()
                .id(id)
                .name("lalala")
                .type("type")
                .build();
        //when
        when(pokemonRepository.findById(id)).thenReturn(Optional.empty());;
        //then
        org.junit.jupiter.api.Assertions.assertThrows(PokemonNotFoundException.class,() -> pokemonService.updatePokemon(pokemonDto,id));
    }

    @Test
    void shouldDeletePokemon_whenFindById(){
        int id = 141;
        Pokemon pokemon = Pokemon.builder()
                .id(id)
                .name("kaka")
                .type("qqqq")
                .build();

        //when
        when(pokemonRepository.findById(id)).thenReturn(Optional.of(pokemon));

        pokemonService.deletePokemonId(id);
        //then
        Mockito.verify(pokemonRepository,times(1)).delete(pokemon);

    }

    @Test
    void shouldThrowPokemonNotFoundException_whenDeletePokemon(){
        int id = 2;

        //when
        when(pokemonRepository.findById(id)).thenReturn(Optional.empty());
        //then
        org.junit.jupiter.api.Assertions.assertThrows(PokemonNotFoundException.class,() -> pokemonService.deletePokemonId(id));


    }
}
