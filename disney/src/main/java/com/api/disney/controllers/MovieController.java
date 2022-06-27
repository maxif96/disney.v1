package com.api.disney.controllers;

import com.api.disney.dtos.MovieDTO;
import com.api.disney.dtos.MovieRequestDTO;
import com.api.disney.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
    public ResponseEntity<MovieDTO> save (@RequestBody MovieRequestDTO movieDTO,
                                          @RequestParam(value = "loadCharacters", required = false, defaultValue = "true") boolean loadCharacters){
        MovieDTO movieSaved = movieService.save(movieDTO, loadCharacters);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAll(@RequestParam(value = "loadCharacters", required = false) boolean loadCharacters){
        List<MovieDTO> movieDTOList = movieService.getAll(loadCharacters);
        return ResponseEntity.ok().body(movieDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update (@PathVariable Long id, @RequestBody MovieDTO movieDTO, boolean loadCharacters){
        MovieDTO movieUpdated = movieService.update(id, movieDTO, loadCharacters);
        return  ResponseEntity.ok().body(movieUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
