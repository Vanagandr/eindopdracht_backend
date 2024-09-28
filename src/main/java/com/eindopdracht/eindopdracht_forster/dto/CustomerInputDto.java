package com.eindopdracht.eindopdracht_forster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CustomerInputDto {

    public Long id;

    @NotBlank(message = "Veld mag niet leeg zijn")
    public String lastName;

    @NotBlank(message = "Veld mag niet leeg zijn")
    public String city;

    @NotBlank(message = "Veld mag niet leeg zijn")
    @Size(min = 2, max = 64, message ="Telefoonnummer is te lang")
    public String phoneNumber;

}
