package com.pokemonreview.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokemonreview.api.controllers.PokemonController;
import com.pokemonreview.api.dto.PokemonDto;
import com.pokemonreview.api.dto.PokemonResponse;
import com.pokemonreview.api.service.PokemonService;
import com.pokemonreview.api.service.impl.PokemonServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = PokemonController.class)
@ExtendWith(MockitoExtension.class)
public class PokemonControllerTests {


    @MockBean
    private PokemonServiceImpl pokemonService;


    private PokemonController pokemonController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        pokemonController = new PokemonController(pokemonService);
        mockMvc = MockMvcBuilders.standaloneSetup(pokemonController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void whenGetPokemonsWithValidInput_thenReturns200() throws Exception {
        int pageNo = 0;
        int pageSize = 10;
        PokemonDto dto1 = PokemonDto.builder()
                .id(2)
                .name("aaaa")
                .type("wwww")
                .build();
        PokemonDto dto2 = PokemonDto.builder()
                .id(3)
                .name("bb")
                .type("qq")
                .build();

        PokemonResponse response1 = PokemonResponse.builder()
                .content(List.of(dto1,dto2))
                .last(true)
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalElements(2)
                .totalPages(1)
                .build();

        when(pokemonService.getAllPokemon(pageNo,pageSize)).thenReturn(response1);

        mockMvc.perform(get("/api/pokemon")
                        .param("pageNo", String.valueOf(pageNo))
                        .param("pageSize", String.valueOf(pageSize))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].name").value("aaaa"))
                .andExpect(jsonPath("$.content[1].name").value("bb"))
                .andExpect(jsonPath("$.pageNo").value(pageNo))
                .andExpect(jsonPath("$.pageSize").value(pageSize));
    }

    @Test
    void shouldReturnPokemonDetail_whenGetIdofPokemon() throws Exception {
        int id = 223;

        PokemonDto pokemonDto = PokemonDto.builder()
                .id(id)
                .type("aa")
                .name("pikachu")
                .build();

        when(pokemonService.getPokemonDto(id)).thenReturn(pokemonDto);

        mockMvc.perform(get("/api/pokemon/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void shouldReturnPokemonDto_whenValidCreatePokemon() throws Exception {
        int id = 223;

        PokemonDto request = PokemonDto.builder()
                .id(id)
                .type("aa")
                .name("pikachu")
                .build();
        PokemonDto response = PokemonDto.builder()
                .id(id)
                .type("aa")
                .name("pikachu")
                .build();

        when(pokemonService.createPoekmon(request)).thenReturn(response);

        mockMvc.perform(post("/api/pokemon/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(request.getId()))
                .andExpect(jsonPath("$.type").value(request.getType()))
                .andExpect(jsonPath("$.name").value(request.getName()));
    }

    @Test
    void shouldReturnPokemonDto_whenValidUpdatePokemon() throws Exception {
        int id = 223;

        PokemonDto request = PokemonDto.builder()
                .id(id)
                .type("aa")
                .name("pikaaachu")
                .build();
        PokemonDto response = PokemonDto.builder()
                .id(id)
                .type("aa")
                .name("pikachu")
                .build();

        when(pokemonService.updatePokemon(request,id)).thenReturn(response);

        mockMvc.perform(put("/api/pokemon/{id}/update",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.type").value(response.getType()))
                .andExpect(jsonPath("$.name").value(response.getName()));
    }


    @Test
    void shouldDeletePokemon_whenValidId() throws Exception {
        int pokemonId = 223;

        // Mock the service to do nothing when deletePokemonId is called
        doNothing().when(pokemonService).deletePokemonId(pokemonId);

        // Perform the DELETE request
        mockMvc.perform(delete("/api/pokemon/{id}/delete", pokemonId))
                .andExpect(status().isOk())
                .andExpect(content().string("Pokemon delete"));

        // Verify that the service method was called with the correct ID
        verify(pokemonService).deletePokemonId(pokemonId);
    }

    @AfterEach
    void tearDown(){
        //
    }


}
