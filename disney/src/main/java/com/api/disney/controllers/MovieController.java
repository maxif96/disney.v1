package com.api.disney.controllers;

import com.api.disney.dtos.MovieResponseDTO;
import com.api.disney.dtos.MovieRequestDTO;
import com.api.disney.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponseDTO> save (@RequestBody MovieRequestDTO movieDTO,
                                                  @RequestParam(value = "loadCharacters", required = false, defaultValue = "true") boolean loadCharacters){
        MovieResponseDTO movieSaved = movieService.save(movieDTO, loadCharacters);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getAll(@RequestParam(value = "loadCharacters", required = false) boolean loadCharacters){
        List<MovieResponseDTO> movieResponseDTOList = movieService.getAll(loadCharacters);
        return ResponseEntity.ok().body(movieResponseDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> update (@PathVariable Long id, @RequestBody MovieResponseDTO movieResponseDTO, boolean loadCharacters){
        MovieResponseDTO movieUpdated = movieService.update(id, movieResponseDTO, loadCharacters);
        return  ResponseEntity.ok().body(movieUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
