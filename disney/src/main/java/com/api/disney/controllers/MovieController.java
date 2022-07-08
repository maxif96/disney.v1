package com.api.disney.controllers;

import com.api.disney.dtos.MovieDTO;
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

    /*----C R U D----*/

    /*----------------SAVE A MOVIE-----------------(CREATE)*/
    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO movieDTO,
                                         @RequestParam(value = "loadCharacters", required = false)
                                         boolean loadCharacters) {
        MovieDTO movieSaved = movieService.save(movieDTO, loadCharacters);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }

    /*----------------GET ALL MOVIES FROM DATABASE-----------------(READ)*/
    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAll(@RequestParam(value = "loadCharacters", required = false)
                                                     boolean loadCharacters) {
        List<MovieDTO> movieDTOList = movieService.getAll(loadCharacters);
        return ResponseEntity.ok().body(movieDTOList);
    }

    /*----------------UPDATE A MOVIE FROM DATABASE BY ID----------------(UPDATE)*/
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id,
                                           @RequestBody MovieDTO movieDTO,
                                           @RequestParam(value = "loadCharacters", required = false)
                                               boolean loadCharacters) {
        MovieDTO movieUpdated = movieService.update(id, movieDTO, loadCharacters);
        return ResponseEntity.ok().body(movieUpdated);
    }

    /*----------------DELETE MOVIE FROM DATABASE BY ID (SOFT)----------------(DELETE)*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /*----------------*/

    /*----------------ADD CHARACTER TO A MOVIE----------------*/
    @PutMapping("/{movieId}/character")
    public ResponseEntity<MovieDTO> addCharacter(@PathVariable Long movieId,
                                                 @RequestBody MovieDTO movieDTO,
                                                 @RequestParam(value = "loadCharacters", required = false)
                                                     boolean loadCharacters) throws Exception {
        MovieDTO movieResponse = movieService.addCharacter(movieId, movieDTO.getCharacters(), loadCharacters);
        return ResponseEntity.ok().body(movieResponse);
    }


}
