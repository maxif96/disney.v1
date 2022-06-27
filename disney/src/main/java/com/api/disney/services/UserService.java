package com.api.disney.services;

import com.api.disney.dtos.LoginDTO;
import com.api.disney.dtos.RegisterDTO;
import com.api.disney.models.UserEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    UserEntity findByUsername(String username) throws Exception;

    String login(LoginDTO loginDTO);
    void register(RegisterDTO registerDTO);

    String tokenRefresh(HttpServletRequest httpServletRequest);

}
