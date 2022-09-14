package com.api.disney.mappers;


import com.api.disney.auth.dto.response.UserResponseDTO;
import com.api.disney.models.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersMapper {


    public UserResponseDTO userEntityToDTO(UserEntity user) {
        return UserResponseDTO
                .builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

    public List<UserResponseDTO> userEntityListToDTOList(List<UserEntity> usersEntity) {
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for (UserEntity users : usersEntity) {
            userResponseDTOList.add(userEntityToDTO(users));
        }
        return userResponseDTOList;
    }

}
