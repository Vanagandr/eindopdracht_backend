package com.eindopdracht.eindopdracht_forster.controller;

import com.eindopdracht.eindopdracht_forster.dto.PartDto;
import com.eindopdracht.eindopdracht_forster.dto.RepairDto;
import com.eindopdracht.eindopdracht_forster.exception.RepairNotFoundException;
import com.eindopdracht.eindopdracht_forster.service.RepairService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/repairs")
public class RepairController {

    private final RepairService repairService;
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @PostMapping
    public ResponseEntity<RepairDto> addRepair(@RequestBody RepairDto repairDto) {
        RepairDto savedRepair = repairService.addRepair(repairDto);
        return ResponseEntity.ok(savedRepair);
    }

    @DeleteMapping({"{id}"})
    public ResponseEntity<String> removeRepair(@PathVariable String id) {
        repairService.removeRepair(id);
        return ResponseEntity.ok("Reparatiehandeling is verwijderd!");
    }

    @GetMapping
    public ResponseEntity<List<RepairDto>> getAllRepairs() {
        List<RepairDto> repairs = repairService.getAllRepairs();
        return ResponseEntity.ok(repairs);
    }
}
