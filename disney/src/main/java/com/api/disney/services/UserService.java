package com.api.disney.services;


import com.api.disney.auth.dto.response.UserResponseDTO;
import com.api.disney.exception.UserNotFoundException;
import com.api.disney.models.UserEntity;

import java.util.List;

public interface UserService {

    List<UserResponseDTO> getAll();

    UserResponseDTO getUserDataByToken(String token) throws UserNotFoundException;

    void deleteUser(Long id) throws Exception;

    UserResponseDTO findById(Long id);

    UserResponseDTO findByEmail(String email) throws UserNotFoundException;
}
