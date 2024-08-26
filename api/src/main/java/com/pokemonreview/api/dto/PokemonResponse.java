package com.pokemonreview.api.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PokemonResponse {
	private List<PokemonDto> content;
	private int pageNo;
	private int pageSize;
	private long totalElements;
	private int totalPages;
	private boolean last;
}
