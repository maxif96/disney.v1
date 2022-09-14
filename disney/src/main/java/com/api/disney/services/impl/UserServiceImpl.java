package com.api.disney.services.impl;


import com.api.disney.auth.UserRepository;
import com.api.disney.auth.dto.response.UserResponseDTO;
import com.api.disney.auth.utils.JwUtils;
import com.api.disney.exception.UserNotFoundException;
import com.api.disney.mappers.UsersMapper;
import com.api.disney.models.UserEntity;
import com.api.disney.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private JwUtils jwUtils;

    public List<UserResponseDTO> getAll() {
        return usersMapper.userEntityListToDTOList(userRepository.findAll());
    }


    @Override
    public UserResponseDTO getUserDataByToken(String token) throws UserNotFoundException {
        String email = jwUtils.extractUsername(token.substring(7));
        UserEntity users = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with that email was not found."));
        return usersMapper.userEntityToDTO(users);
    }


    public void deleteUser(Long id) throws Exception {
        Optional<UserEntity> response = userRepository.findById(id);
        if (response.isPresent()) {
            UserEntity users = response.get();
            userRepository.delete(users);
        } else {
            throw new Exception("User can not be deleted.");
        }
    }

    @Override
    public UserResponseDTO findById(Long id) {
        Optional<UserEntity> res = userRepository.findById(id);
        if (res.isPresent()) {
            UserEntity user = res.get();
            return usersMapper.userEntityToDTO(user);
        } else {
            throw new EntityNotFoundException("UserNotFound");
        }
    }

    @Override
    public UserResponseDTO findByEmail(String email) throws UserNotFoundException {
        UserEntity user = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with that email was not found."));
        return usersMapper.userEntityToDTO(user);
    }

}



