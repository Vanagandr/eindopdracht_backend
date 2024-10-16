package com.eindopdracht.eindopdracht_forster.service;

import com.eindopdracht.eindopdracht_forster.dto.RepairDto;
import com.eindopdracht.eindopdracht_forster.exception.RepairNotFoundException;
import com.eindopdracht.eindopdracht_forster.mapper.RepairDtoMapper;
import com.eindopdracht.eindopdracht_forster.model.Repair;
import com.eindopdracht.eindopdracht_forster.repository.RepairRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RepairService {

    private final RepairRepository repairRepository;
    private final RepairDtoMapper repairDtoMapper;

    public RepairService(RepairRepository repairRepository, RepairDtoMapper repairDtoMapper) {
        this.repairRepository = repairRepository;
        this.repairDtoMapper = repairDtoMapper;
    }

    //Add a repair
    public RepairDto addRepair(RepairDto repairDto) {
        Repair repair = repairDtoMapper.repairDtoToRepairMapper(repairDto);
        Repair savedRepair = repairRepository.save(repair);
        return repairDtoMapper.repairToDtoMapper(savedRepair);
    }

    //Remove a repair
    public void removeRepair(String id){
        if(repairRepository.existsById(id)){
            repairRepository.deleteById(id);
        } else {
            throw new RepairNotFoundException("Reparatiehandeling niet gevonden");
        }
    }

    //Get all the repairs
    public List<RepairDto> getAllRepairs() {
        List<Repair> repairs = repairRepository.findAll();
        return repairs.stream()
                .map(repairDtoMapper::repairToDtoMapper)
                .collect(Collectors.toList());
    }

}
