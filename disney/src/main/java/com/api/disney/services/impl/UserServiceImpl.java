package com.api.disney.services.impl;

import com.api.disney.dtos.LoginDTO;
import com.api.disney.dtos.RegisterDTO;
import com.api.disney.mappers.UserMapper;
import com.api.disney.models.UserEntity;
import com.api.disney.repositories.UserRepository;
import com.api.disney.security.JwUtil;
import com.api.disney.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpServletRequest;

import static com.api.disney.security.util.Role.ROLE_USER;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwUtil jwUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authentication;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserMapper userMapper;

    public UserEntity findByUsername(String username) throws Exception {
        return userRepository.findByUsername(username).orElseThrow(() -> new Exception("Username invalid"));
    }

    public String login(LoginDTO loginDTO) {
        try {
            String username = loginDTO.getUsername(), password = loginDTO.getPassword();

            Authentication auth = authentication.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            UserDetails user = (UserDetails) auth.getPrincipal();

            return jwUtil.generateToken(user, 10);
        } catch (Exception e) {
            throw new BadCredentialsException(e.getLocalizedMessage() + ": incorrect e-mail or password or email not confirm");
        }

    }

    public void register(RegisterDTO registerDTO) {

        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new RuntimeException("The email already in use");
        }
        String password = passwordEncoder.encode(registerDTO.getPassword());

        UserEntity user = userMapper.DTOToEntity(registerDTO);
        user.setRole(ROLE_USER);
        user.setPassword(password);
        userRepository.save(user);
    }

    public String tokenRefresh(HttpServletRequest httpServletRequest) {
        final String authorizationHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        String username = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            username = jwUtil.extractUsername(jwt);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        return jwUtil.generateToken(userDetails, 20);
    }
}



