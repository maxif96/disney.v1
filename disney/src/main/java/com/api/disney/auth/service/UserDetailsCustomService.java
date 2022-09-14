package com.api.disney.auth.service;

import com.api.disney.auth.UserRepository;
import com.api.disney.auth.dto.request.AuthenticationRequest;
import com.api.disney.auth.dto.request.UserDTO;
import com.api.disney.auth.dto.response.Jwt;
import com.api.disney.auth.service.mapper.UserAuthMapper;
import com.api.disney.auth.utils.JwUtils;
import com.api.disney.exception.EmailAlreadyExists;
import com.api.disney.exception.UserNotFoundException;
import com.api.disney.models.Role;
import com.api.disney.models.UserEntity;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserAuthMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwUtils jwUtils;


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User was not found."));

        Collection<GrantedAuthority> authorities = Collections
                .singleton(new SimpleGrantedAuthority(user.getRole().getAuthority()));

        return new User(user.getEmail(), user.getPassword(), authorities);

    }

    @Transactional
    public Jwt save (UserDTO userDTO) throws IOException, EmailAlreadyExists {
        String encryptPass = passwordEncoder.encode(userDTO.getPassword());
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent()) throw new EmailAlreadyExists("Email is already in use");
        UserEntity user = userMapper.userDTOtoEntity(userDTO);
        user.setPassword(encryptPass);
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        String jwt = jwUtils.generateToken(loadUserByUsername(user.getEmail()));

        return Jwt.builder()
                .token(jwt)
                .build();
    }

    public Jwt authentication (AuthenticationRequest authenticationRequest){
        if (!userRepository.existsByEmail(authenticationRequest.getEmail())) throw new BadCredentialsException("Email doesn't exist");
        UserDetails userDetails;
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Email or password incorrect", e);
        }

        final String jwt =  jwUtils.generateToken(userDetails);

        return Jwt.builder()
                .token(jwt)
                .build();
    }
}
