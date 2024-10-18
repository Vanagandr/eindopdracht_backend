package com.eindopdracht.eindopdracht_forster.mapper;


import com.eindopdracht.eindopdracht_forster.model.Repair;
import com.eindopdracht.eindopdracht_forster.dto.RepairDto;
import org.springframework.stereotype.Component;

@Component
public class RepairDtoMapper {

    //Mapping from Repair to RepairDto
    public RepairDto repairToDtoMapper (Repair repair) {
        RepairDto dto = new RepairDto();
        dto.type = repair.getType();
        dto.price = repair.getPrice();
        return dto;
    }

    //Mapping from RepairDto to Repair
    public Repair repairDtoToRepairMapper (RepairDto dto) {
        Repair repair = new Repair();
        repair.setType(dto.type);
        repair.setPrice(dto.price);
        return repair;
    }
}

