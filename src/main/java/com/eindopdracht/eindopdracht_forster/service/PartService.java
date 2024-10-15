package com.eindopdracht.eindopdracht_forster.service;

import com.eindopdracht.eindopdracht_forster.exception.PartNotFoundException;
import com.eindopdracht.eindopdracht_forster.mapper.PartDtoMapper;
import com.eindopdracht.eindopdracht_forster.dto.PartDto;
import com.eindopdracht.eindopdracht_forster.repository.PartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartService {

    private final PartRepository partRepository;
    private final PartDtoMapper partDtoMapper;
    public PartService(PartRepository partRepository, PartDtoMapper partDtoMapper) {
        this.partRepository = partRepository;
        this.partDtoMapper = partDtoMapper;
    }
    public PartDto addPart(PartDto partDto) {
        com.eindopdracht.eindopdracht_forster.model.Part existingPart = partRepository.findByType(partDto.type);
        if (existingPart != null) {
            existingPart.setQuantity(existingPart.getQuantity() + partDto.quantity);
            partRepository.save(existingPart);
            return partDtoMapper.partToDtoMapper(existingPart);
        } else {
            com.eindopdracht.eindopdracht_forster.model.Part newPart = partDtoMapper.partDtoToPartMapper(partDto);
            newPart.setQuantity(partDto.quantity);
            partRepository.save(newPart);
            return partDtoMapper.partToDtoMapper(newPart);
        }
    }
    public String removePart(String type) {
        com.eindopdracht.eindopdracht_forster.model.Part part = partRepository.findByType(type);
        if (part != null) {
            partRepository.delete(part);
            return ("Het onderdeel is verwijderd");
        }else{
            throw new PartNotFoundException("Dit onderdeel is niet gevonden");
        }
    }

    public List<PartDto> getAllParts() {
        List<com.eindopdracht.eindopdracht_forster.model.Part> parts = partRepository.findAll();
        return parts.stream()
                .map(partDtoMapper::partToDtoMapper) // Map Part to PartDto
                .collect(Collectors.toList());
    }

    public PartDto updatePart(String type, PartDto updatedPartDto) {
        com.eindopdracht.eindopdracht_forster.model.Part existingPart = partRepository.findByType(type);
        if (existingPart == null) {
            throw new PartNotFoundException("Dit onderdeel is niet gevonden");
        }
        existingPart.setPrice(updatedPartDto.price);
        existingPart.setQuantity(updatedPartDto.quantity);

        partRepository.save(existingPart);

        return partDtoMapper.partToDtoMapper(existingPart);
    }

}

