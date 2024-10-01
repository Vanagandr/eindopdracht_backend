package com.eindopdracht.eindopdracht_forster.dto;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class CarDto {

    @NotBlank(message = "Veld mag niet leeg zijn")
    public String registration;

    @NotBlank(message = "Veld mag niet leeg zijn")
    public String brand;

    @NotBlank(message = "Veld mag niet leeg zijn")
    public String yearOfConstruct;

    @NotBlank(message = "Veld mag niet leeg zijn")
    public LocalDate inspectDay;

    @NotBlank(message = "Veld mag niet leeg zijn")
    public LocalDate repairDay;

}
