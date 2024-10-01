package com.eindopdracht.eindopdracht_forster.dto;
import com.eindopdracht.eindopdracht_forster.model.Customer;
import com.eindopdracht.eindopdracht_forster.model.Repair;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;

public class CarDto {

    @NotBlank(message = "Veld mag niet leeg zijn")
    public String registration;

    @NotBlank(message = "Veld mag niet leeg zijn")
    public String brand;

    @NotBlank(message = "Veld mag niet leeg zijn")
    public String yearOfConstruct;

    @NotBlank(message = "Veld mag niet leeg zijn")
    public LocalDate inspectionDate;

    @NotBlank(message = "Veld mag niet leeg zijn")
    public LocalDate repairDate;

    public CustomerOutputDto customer;

    public List<Repair> neededRepairs;

    public List<Repair> doneRepairs;



}
