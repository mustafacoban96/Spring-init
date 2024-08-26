package com.pokemonreview.api.service.impl;



import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.exceptions.PokemonNotFoundException;
import com.pokemonreview.api.model.Pokemon;
import com.pokemonreview.api.repository.PokemonRepository;
import com.pokemonreview.api.service.PokemonService;


@Service
public class PokemonServiceImpl implements PokemonService{
	
	private PokemonRepository pokemonRepository;
	
	
	@Autowired // we inject Repo -> Service
	public PokemonServiceImpl(PokemonRepository pokemonRepository) {
		this.pokemonRepository = pokemonRepository;
	}

	@Override
	public PokemonDto createPoekmon(PokemonDto pokemonDto) {
		Pokemon pokemon = new Pokemon();
		pokemon.setName(pokemonDto.getName());
		pokemon.setType(pokemonDto.getType());
		
		Pokemon newPokemon = pokemonRepository.save(pokemon);
		PokemonDto pokemonResponse = PokemonDto.builder()
				.id(newPokemon.getId())
				.name(newPokemon.getName())
				.type(newPokemon.getType()).
				build();
		
		return pokemonResponse;
	}
	
	

	@Override
	public PokemonResponse getAllPokemon(int pageNo, int pageSize) {
		/*
		 * Firstly, the findAll(Pageable pageable) method. 
		 * This method accepts a Pageable object that represents pagination information. 
		 * This method returns a Page object meeting the pagination restriction provided in the Pageable object. 
		 * Page is a sublist of a list of objects. 
		 * A Page object provides information about its position in the containing list.*/
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		// map because it returns a list
		Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
		List<Pokemon> listOfPokemons = pokemons.getContent();
		List<PokemonDto> content = listOfPokemons.stream().map(pokemon -> mapToDto(pokemon)).collect(Collectors.toList()); 
		
		PokemonResponse pokemonResponse = PokemonResponse.builder()
				.content(content)
				.pageNo(pokemons.getNumber())
				.pageSize(pokemons.getSize())
				.totalPages(pokemons.getTotalPages())
				.totalElements(pokemons.getTotalElements())
				.last(pokemons.isLast())
				.build();

		
		return pokemonResponse;
	}
	
	
	private PokemonDto mapToDto(Pokemon pokemon) {
		
		PokemonDto pokemonDto = PokemonDto.builder()
				.id(pokemon.getId())
				.name(pokemon.getName())
				.type(pokemon.getType())
				.build();
		return pokemonDto;
		
	}
	private Pokemon mapToEntity(PokemonDto pokemonDto) {
		
		Pokemon pokemon = new Pokemon();
		pokemon.setId(pokemonDto.getId());
		pokemon.setName(pokemonDto.getName());
		pokemon.setType(pokemonDto.getType());
		
		return pokemon;
	}

	@Override
	public PokemonDto getPokemonDto(int id) {
		Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be found!!!"));
		return mapToDto(pokemon);
	}

	@Override
	public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
		Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be updated"));
		
		pokemon.setName(pokemonDto.getName());
		pokemon.setType(pokemonDto.getType());
		
		Pokemon updatedPokemon = pokemonRepository.save(pokemon);
		
		return mapToDto(updatedPokemon);
	}

	@Override
	public void deletePokemonId(int id) {
		
		Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new PokemonNotFoundException("Pokemon could not be delete.."));
		pokemonRepository.delete(pokemon);
		
	}

}
