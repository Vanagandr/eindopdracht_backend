package com.eindopdracht.eindopdracht_forster.service;

import com.eindopdracht.eindopdracht_forster.dto.PartDto;
import com.eindopdracht.eindopdracht_forster.exception.PartNotFoundException;
import com.eindopdracht.eindopdracht_forster.mapper.PartDtoMapper;
import com.eindopdracht.eindopdracht_forster.repository.PartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PartServiceTest {

    @Mock
    private PartRepository partRepository;

    @Mock
    private PartDtoMapper partDtoMapper;

    @InjectMocks
    private PartService partService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test

    //Test add new part
    public void testAddPart_NewPart() {
        PartDto partDto = new PartDto();
        partDto.type = "Engine";
        partDto.quantity = 10;

        com.eindopdracht.eindopdracht_forster.model.Part part = new com.eindopdracht.eindopdracht_forster.model.Part();
        part.setType(partDto.type);
        part.setQuantity(partDto.quantity);

        when(partRepository.findByType(partDto.type)).thenReturn(null);
        when(partDtoMapper.partDtoToPartMapper(partDto)).thenReturn(part);
        when(partRepository.save(part)).thenReturn(part);
        when(partDtoMapper.partToDtoMapper(part)).thenReturn(partDto);

        PartDto result = partService.addPart(partDto);

        assertEquals(partDto.type, result.type);
    }

    // test remove part
    @Test
    public void testRemovePart() {
        String partType = "Engine";
        com.eindopdracht.eindopdracht_forster.model.Part part = new com.eindopdracht.eindopdracht_forster.model.Part();
        part.setType(partType);

        when(partRepository.findByType(partType)).thenReturn(part);

        String result = partService.removePart(partType);

        assertEquals("Het onderdeel is verwijderd", result);
    }

    //Test get all parts
    @Test
    public void testGetAllParts() {
        com.eindopdracht.eindopdracht_forster.model.Part part1 = new com.eindopdracht.eindopdracht_forster.model.Part();
        part1.setType("Engine");
        part1.setQuantity(10);

        com.eindopdracht.eindopdracht_forster.model.Part part2 = new com.eindopdracht.eindopdracht_forster.model.Part();
        part2.setType("Brake");
        part2.setQuantity(5);

        List<com.eindopdracht.eindopdracht_forster.model.Part> parts = Arrays.asList(part1, part2);
        when(partRepository.findAll()).thenReturn(parts);

        PartDto partDto1 = new PartDto();
        partDto1.type = "Engine";

        PartDto partDto2 = new PartDto();
        partDto2.type = "Brake";

        when(partDtoMapper.partToDtoMapper(part1)).thenReturn(partDto1);
        when(partDtoMapper.partToDtoMapper(part2)).thenReturn(partDto2);

        List<PartDto> result = partService.getAllParts();

        assertEquals(2, result.size());
    }

    //Test update part
    @Test
    public void testUpdatePart() {
        String partType = "Engine";
        PartDto updatedPartDto = new PartDto();
        updatedPartDto.price = 100.0;
        updatedPartDto.quantity = 15;

        com.eindopdracht.eindopdracht_forster.model.Part existingPart = new com.eindopdracht.eindopdracht_forster.model.Part();
        existingPart.setType(partType);
        existingPart.setPrice(90.0);
        existingPart.setQuantity(10);

        when(partRepository.findByType(partType)).thenReturn(existingPart);
        when(partRepository.save(existingPart)).thenReturn(existingPart);
        when(partDtoMapper.partToDtoMapper(existingPart)).thenReturn(updatedPartDto);


        PartDto result = partService.updatePart(partType, updatedPartDto);

        assertEquals(updatedPartDto.price, existingPart.getPrice());
        assertEquals(updatedPartDto.quantity, existingPart.getQuantity());
    }

}
