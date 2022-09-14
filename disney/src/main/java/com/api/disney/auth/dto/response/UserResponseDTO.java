package com.api.disney.auth.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class UserResponseDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;

}
