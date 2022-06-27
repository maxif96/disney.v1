package com.api.disney.mappers;

import com.api.disney.dtos.RegisterDTO;
import com.api.disney.models.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity DTOToEntity(RegisterDTO registerDTO){
        return UserEntity.builder()
                .username(registerDTO.getUsername())
                .email(registerDTO.getEmail())
                .build();
    }

}
