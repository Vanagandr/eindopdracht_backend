package com.eindopdracht.eindopdracht_forster.dto;


import jakarta.validation.constraints.NotBlank;

public class PartDto {

    @NotBlank(message = "Type cannot be blank")
    public String type;

    @NotBlank(message = "Price cannot be blank")
    public double price;

    public int quantity;
}

