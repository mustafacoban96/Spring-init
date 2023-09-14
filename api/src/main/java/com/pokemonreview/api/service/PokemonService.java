package com.pokemonreview.api.service;

import com.pokemonreview.api.dto.PokemonDto;

/* DTO entity datalarında fieldları katmanlar arası transfer edilmesine yarayan 
 * nesnelerdir.
 * Katmanlar Arası Bağımlılığın Azaltılması ise diğer bir önemli etkendir.
 * Yazılım sistemleri genellikle katmanlar halinde organize edilir.
 * https://medium.com/@latestsoftwaredevelopers/dto-nedir-ne-işe-yarar-8f01c9e7bba7 
 */

public interface PokemonService {

	PokemonDto createPoekmon(PokemonDto pokemonDto);
	

	
}
