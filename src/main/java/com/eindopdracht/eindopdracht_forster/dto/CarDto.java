package com.eindopdracht.eindopdracht_forster.dto;
import com.eindopdracht.eindopdracht_forster.model.Customer;
import com.eindopdracht.eindopdracht_forster.model.Part;
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

    //No validation for components because they do not always exist.
    public LocalDate inspectionDate;

    public LocalDate repairDate;

    public CustomerOutputDto customer;

    public List<Repair> neededRepairs;

    public List<Repair> doneRepairs;

    public List<Part> usedParts;

    public boolean agreerepair;



}
