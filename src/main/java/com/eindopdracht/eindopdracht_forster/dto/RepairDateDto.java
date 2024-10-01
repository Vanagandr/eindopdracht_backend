package com.eindopdracht.eindopdracht_forster.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class RepairDateDto {
    @NotNull(message = "Reparatie datum kan niet leeg zijn")
    public LocalDate repairDate;
}

