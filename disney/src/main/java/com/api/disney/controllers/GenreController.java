package com.api.disney.controllers;

import com.api.disney.dtos.GenreDTO;
import com.api.disney.models.Genre;
import com.api.disney.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {

    @Autowired
    GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreDTO> save (@RequestBody GenreDTO genreDTO){
        GenreDTO genreSaved = genreService.save(genreDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreSaved);
    }

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll(){
        List<GenreDTO> genreDTOList = genreService.getAll();
        return ResponseEntity.ok().body(genreDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable Long id,
                                           @RequestBody GenreDTO genreDTO){
        GenreDTO genreUpdated = genreService.update(id, genreDTO);
        return ResponseEntity.ok().body(genreUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        genreService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
