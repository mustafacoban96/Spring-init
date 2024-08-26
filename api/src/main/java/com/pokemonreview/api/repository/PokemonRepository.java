package com.pokemonreview.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pokemonreview.api.model.Pokemon;

import java.util.Optional;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{

    Optional<Pokemon> findByType(String type);

}
