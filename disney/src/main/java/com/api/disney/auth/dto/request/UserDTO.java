package com.api.disney.auth.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @NotBlank(message = "Email format incorrect.")
    @Email(message = "Email format incorrect.")
    private String email;

    @Size(min = 8, max = 20,
            message = "Password must be greater than 8 characters and less than 20")
    private String password;

    @NotBlank(message = "Name can not be empty.")
    private String name;

    @NotBlank(message = "Lastname can not be empty.")
    private String lastName;


}
