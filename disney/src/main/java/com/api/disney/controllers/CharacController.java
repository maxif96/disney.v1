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

    @GetMapping
    public ResponseEntity<List<CharacDTO>> getAll(){
        List<CharacDTO> characDTOList = characService.getAll();
        return ResponseEntity.ok().body(characDTOList);
    }

    @PostMapping
    public ResponseEntity<CharacDTO> save(@RequestBody CharacDTO characDTO){
        CharacDTO characSaved = characService.save(characDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(characSaved);
    }

}
