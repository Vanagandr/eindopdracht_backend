package com.eindopdracht.eindopdracht_forster.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDto {

    @NotBlank(message = "Veld mag niet leeg zijn")
    public String username;

    @NotBlank(message = "Veld mag niet leeg zijn")
    public String password;

    @NotBlank(message = "Veld mag niet leeg zijn")
    public String[] roles;
}


