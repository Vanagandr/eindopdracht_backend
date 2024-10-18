package com.eindopdracht.eindopdracht_forster.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RepairDto {

    @NotBlank(message = "Veld mag niet leeg zijn")
    public String type;

    @NotNull(message = "Veld mag niet leeg zijn")
    public double price;
}
