package com.eindopdracht.eindopdracht_forster.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class InspectionDto {
    @NotNull(message = "Inspectie datum kan niet leeg zijn")
    public LocalDate inspectionDate;
}
