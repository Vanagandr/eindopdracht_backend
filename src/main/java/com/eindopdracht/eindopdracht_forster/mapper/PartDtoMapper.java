package com.eindopdracht.eindopdracht_forster.mapper;

import com.eindopdracht.eindopdracht_forster.dto.PartDto;
import com.eindopdracht.eindopdracht_forster.model.Part;
import org.springframework.stereotype.Component;

@Component
public class PartDtoMapper {

    //Mapping from Part to PartDto
    public PartDto partToDtoMapper(Part part) {
        PartDto dto = new PartDto();
        dto.type = part.getType();
        dto.price = part.getPrice();
        dto.quantity = part.getQuantity();
        return dto;
    }

    //Mapping from PartDto to Part
    public Part partDtoToPartMapper(PartDto dto) {
        Part part = new Part();
        part.setType(dto.type);
        part.setPrice(dto.price);
        part.setQuantity(dto.quantity);
        return part;
    }

}