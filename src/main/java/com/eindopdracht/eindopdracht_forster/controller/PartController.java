package com.eindopdracht.eindopdracht_forster.controller;

import com.eindopdracht.eindopdracht_forster.dto.PartDto;
import com.eindopdracht.eindopdracht_forster.service.PartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parts")
public class PartController {

    private final PartService partService;
    public PartController(PartService partService) {
        this.partService = partService;
    }
    // Add part to database
    @PostMapping
    public ResponseEntity<PartDto> addPart(@RequestBody PartDto partDto) {
        PartDto part = partService.addPart(partDto);
        return ResponseEntity.ok(part);
    }
    //Delete part from database
    @DeleteMapping("/{type}")
    public ResponseEntity<String> removePart(@PathVariable String type) {
        String response = partService.removePart(type);
        return ResponseEntity.ok(response);
    }
    // Get list of all parts from database
    @GetMapping
    public ResponseEntity<List<PartDto>> getAllParts() {
        List<PartDto> parts = partService.getAllParts();
        return ResponseEntity.ok(parts);
    }
    //Update parts in the database.
    @PutMapping("/{type}")
    public ResponseEntity<PartDto> updatePart(@PathVariable String type, @RequestBody PartDto updatedPartDto) {
        PartDto updatedPart = partService.updatePart(type, updatedPartDto);
        return ResponseEntity.ok(updatedPart);
    }
}
