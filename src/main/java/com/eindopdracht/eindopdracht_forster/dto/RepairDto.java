package com.eindopdracht.eindopdracht_forster.dto;

import jakarta.validation.constraints.NotBlank;

public class RepairDto {

    @NotBlank(message = "Veld mag niet leeg zijn")
    public String type;

    @NotBlank(message = "Veld mag niet leeg zijn")
    public double price;
}
