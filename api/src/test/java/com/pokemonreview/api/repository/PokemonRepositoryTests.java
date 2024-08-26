package com.pokemonreview.api.repository;


import com.pokemonreview.api.model.Pokemon;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PokemonRepositoryTests {

    @Autowired
    private PokemonRepository pokemonRepository;


    @Test
    void PokemonRepository_SaveAll_ReturnSavedPokemon(){
        //Arrange
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();
        //Act
        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        //Assert
        Assertions.assertThat(savedPokemon).isNotNull();
        Assertions.assertThat(savedPokemon.getId()).isGreaterThan(0);
    }


    //List<Pokemon> pokemonList = pokemonRepository.findAll();
    @Test
    void PokemonRepository_GetAll_ReturnsMoreThenOnePokemon(){
        //Arrange
        Pokemon pokemon = Pokemon.builder().name("pkiac").type("qqq").build();
        Pokemon pokemon2 = Pokemon.builder().name("pkiac").type("qqq").build();

        ////
        pokemonRepository.save(pokemon);
        pokemonRepository.save(pokemon2);
        //Act
        List<Pokemon> pokemonList = pokemonRepository.findAll();

        //Assert
        Assertions.assertThat(pokemonList).isNotNull();
        Assertions.assertThat(pokemonList.size()).isEqualTo(2);
    }

    @Test
    void PokemonRepository_FindById_ReturnOnePokemon(){

        Pokemon pokemon = Pokemon.builder().name("pkiac").type("qqq").build();
        pokemonRepository.save(pokemon);
        Pokemon pok = pokemonRepository.findById(pokemon.getId()).get();
        Assertions.assertThat(pok).isNotNull();

    }

    @Test
    void PokemonRepository_findByType_ReturnPokemonNotNull(){

        Pokemon pokemon = Pokemon.builder().name("pikaç").type("all").build();
        pokemonRepository.save(pokemon);
        Pokemon pok = pokemonRepository.findByType(pokemon.getType()).get();
        Assertions.assertThat(pok).isNotNull();


    }

    @Test
    void PokemonRepository_UpdatePokemon_ReturnPokemonNotNull(){
        Pokemon pokemon = Pokemon.builder().name("pikaç").type("all").build();
        pokemonRepository.save(pokemon);

        Pokemon pok = pokemonRepository.findById(pokemon.getId()).get();
        pok.setType("electric");
        pok.setName("lala");

        Pokemon uptPok = pokemonRepository.save(pok);
        Assertions.assertThat(uptPok.getName()).isNotNull();
        Assertions.assertThat(uptPok.getType()).isNotNull();
    }

    @Test
    void PokemonRepository_PokemonDelete_ReturnPokemonNotNull(){
        Pokemon pokemon = Pokemon.builder().name("pikaç").type("all").build();
        pokemonRepository.save(pokemon);

        pokemonRepository.deleteById(pokemon.getId());

        Optional<Pokemon> pokemonReturn = pokemonRepository.findById(pokemon.getId());
        Assertions.assertThat(pokemonReturn).isEmpty();

    }
}
