package com.api.disney.auth.controller;

import com.api.disney.auth.dto.request.AuthenticationRequest;
import com.api.disney.auth.dto.request.UserDTO;
import com.api.disney.auth.dto.response.Jwt;
import com.api.disney.auth.dto.response.UserResponseDTO;
import com.api.disney.auth.service.UserDetailsCustomService;
import com.api.disney.auth.utils.JwUtils;
import com.api.disney.exception.UserNotFoundException;
import com.api.disney.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserAuthController {



    private AuthenticationManager authenticationManager;
    private JwUtils jwUtils;

    @Autowired private UserService userService;


    @Autowired
    private UserDetailsCustomService userDetailsCustomService;


    @PostMapping("/register")
    public ResponseEntity<Jwt> register(@Valid @RequestBody UserDTO user) throws Exception {

        Jwt jwt = userDetailsCustomService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(jwt);
    }


    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getUserData(
            @RequestHeader(name = "Authorization") String token
    ) throws UserNotFoundException {
        return ResponseEntity.ok().body(userService.getUserDataByToken(token));
    }


    @PostMapping("/login")
    public ResponseEntity<Jwt> login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {

        Jwt jwt = userDetailsCustomService.authentication(authenticationRequest);

        return ResponseEntity.ok(jwt);

    }

}
