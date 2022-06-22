package controllers;

import com.api.disney.dtos.CharacDTO;
import com.api.disney.services.CharacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("characters")
public class CharacController {

    @Autowired
    private CharacService characService;

    @PostMapping
    public ResponseEntity<CharacDTO> save(@RequestBody CharacDTO characDTO){
        CharacDTO characSaved = characService.save(characDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(characSaved);
    }

}
