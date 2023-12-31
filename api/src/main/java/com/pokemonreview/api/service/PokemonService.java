package com.pokemonreview.api.service;

import java.util.List;

import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;

/* DTO entity datalarında fieldları katmanlar arası transfer edilmesine yarayan 
 * nesnelerdir.
 * Katmanlar Arası Bağımlılığın Azaltılması ise diğer bir önemli etkendir.
 * Yazılım sistemleri genellikle katmanlar halinde organize edilir.
 * https://medium.com/@latestsoftwaredevelopers/dto-nedir-ne-işe-yarar-8f01c9e7bba7 
 */

public interface PokemonService {

	PokemonDto createPoekmon(PokemonDto pokemonDto);
	PokemonResponse getAllPokemon(int pageNo, int pageSize);
	PokemonDto getPokemonDto(int id);
	PokemonDto updatePokemon(PokemonDto pokemonDto,int id);
	void deletePokemonId(int id);

	
}
