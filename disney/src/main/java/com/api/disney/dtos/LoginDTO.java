package com.api.disney.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginDTO {

    @NotBlank
    @NotNull
    private String username;
    @NotBlank
    @NotNull
    private String password;

}
