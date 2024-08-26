package com.pokemonreview.api.repository;

import com.pokemonreview.api.service.impl.PokemonServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class MyTestClass {

    private PokemonRepository pokemonRepository;
    private PokemonServiceImpl pokemonService;


    @BeforeEach
    void setUp(){
        pokemonRepository = Mockito.mock(PokemonRepository.class);
        pokemonService = new PokemonServiceImpl(pokemonRepository);
    }

    @AfterEach
    void tearDown(){
        //
    }

}
