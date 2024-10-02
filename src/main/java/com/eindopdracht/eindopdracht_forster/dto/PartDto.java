package com.eindopdracht.eindopdracht_forster.dto;


import jakarta.validation.constraints.NotBlank;

public class PartDto {

    @NotBlank(message = "Type kan niet leeg zijn")
    public String type;

    @NotBlank(message = "Prijs kan niet leeg zijn")
    public double price;

    public int quantity;
}

