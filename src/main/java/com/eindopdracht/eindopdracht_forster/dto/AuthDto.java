package com.eindopdracht.eindopdracht_forster.dto;

import jakarta.validation.constraints.NotBlank;

public class AuthDto {
    @NotBlank
    public String username;

    @NotBlank
    public String password;
}
