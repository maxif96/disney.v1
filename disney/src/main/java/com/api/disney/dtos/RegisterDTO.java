package com.api.disney.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class RegisterDTO {

    @NotBlank
    @NotNull
    @Email
    private String email;
    @NotBlank
    @NotNull
    private String username;
    @NotBlank
    @NotNull
    private String password;

}
