package com.api.disney.controllers;

import com.api.disney.dtos.CharacDTO;
import com.api.disney.services.CharacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("characters")
public class CharacController {

    @Autowired
    private CharacService characService;

    /*----C R U D----*/

    /*----------------SAVE A CHARACTER-----------------(CREATE)*/
    @PostMapping
    public ResponseEntity<CharacDTO> save(@RequestBody CharacDTO characDTO,
                                          @RequestParam(value = "loadMovies", required = false)
                                          boolean loadMovies) {
        CharacDTO characSaved = characService.save(characDTO, loadMovies);
        return ResponseEntity.status(HttpStatus.CREATED).body(characSaved);
    }

    /*----------------GET ALL CHARACTERS FROM DATABASE-----------------(READ)*/
    @GetMapping
    public ResponseEntity<List<CharacDTO>> getAll(@RequestParam(value = "loadMovies", required = false)
                                                      boolean loadMovies) {
        List<CharacDTO> characDTOList = characService.getAll(loadMovies);
        return ResponseEntity.ok().body(characDTOList);
    }

    /*----------------UPDATE CHARACTER FROM DATABASE BY ID----------------(UPDATE)*/
    @PutMapping("/{id}")
    public ResponseEntity<CharacDTO> update(@PathVariable Long id,
                                            @RequestBody CharacDTO characDTO,
                                            @RequestParam(value = "loadMovies", required = false)
                                                boolean loadMovies) {
        CharacDTO characUpdate = characService.update(id, characDTO, loadMovies);
        return ResponseEntity.ok().body(characUpdate);
    }

    /*----------------DELETE CHARACTER FROM DATABASE BY ID (SOFT)----------------(DELETE)*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        characService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    /*-----------------*/


}
