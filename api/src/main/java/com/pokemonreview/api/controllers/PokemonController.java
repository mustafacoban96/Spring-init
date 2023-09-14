package com.pokemonreview.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.model.Pokemon;
import com.pokemonreview.api.service.PokemonService;

@RestController
@RequestMapping("/api/")
public class PokemonController {
	
	private PokemonService pokemonService;
	
	@Autowired
	public PokemonController(PokemonService pokemonService) {
		this.pokemonService = pokemonService;
	}


	//ResponseEntity sınıfı HttpEntity sınıfına ek olarak builder tasarım desenini kullanarak isteğe yanıt olarak header bilgisi, HTTP durum kodu gibi bilgileri eklemeyi sağlar.
	@GetMapping("pokemon") // => http://localhost:8080/api/pokemon
	public ResponseEntity<List<Pokemon>> getPokemons(){
		List<Pokemon> pokemons = new ArrayList<>();
		pokemons.add(new Pokemon(1, "Squirtle", "Water"));
		pokemons.add(new Pokemon(2,"Pikachu","Electric"));
		pokemons.add(new Pokemon(3,"Chamander","Fire"));
		return ResponseEntity.ok(pokemons);
	}
	
	
	@GetMapping("pokemon/{id}")
	public Pokemon pokemonDetail(@PathVariable int id) {
		 return  new Pokemon(id,"Squirtle","Water");
	}
	/////////////////////////////////////////////////////////////////
	
	/*@PostMapping("pokemon/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon){
		System.out.println(pokemon.getName());
		System.out.println(pokemon.getType());
		
		return new ResponseEntity<>(pokemon,HttpStatus.CREATED);
	}*/
	
	// injected version
	
	@PostMapping("pokemon/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto){
		return new ResponseEntity<>(pokemonService.createPoekmon(pokemonDto),HttpStatus.CREATED);
	}
	
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	@PutMapping("pokemon/{id}/update")
	public ResponseEntity<Pokemon> updatePokemon(@RequestBody Pokemon pokemon, @PathVariable("id") int pokemonId){
		System.out.println(pokemon.getName());
		System.out.println(pokemon.getType());
		return ResponseEntity.ok(pokemon);
	}
	
	
	@DeleteMapping("pokemon/{id}/delete")
	public ResponseEntity<String> deletePokemon(@PathVariable("id") int pokemonId){
		System.out.println(pokemonId);
		return ResponseEntity.ok("Pokemon was deleted successfully");
	}
	
	
	
	
	
	
	
	
	
	
	
}
