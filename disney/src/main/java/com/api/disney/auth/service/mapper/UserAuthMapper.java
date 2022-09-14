package com.api.disney.auth.service.mapper;

import com.api.disney.auth.dto.request.UserDTO;
import com.api.disney.models.UserEntity;
import org.springframework.stereotype.Component;


@Component
public class UserAuthMapper {

    public UserDTO userEntityToDTO(UserEntity user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .build();
    }

    public UserEntity userDTOtoEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .lastName(userDTO.getLastName())
                .build();
    }


}
