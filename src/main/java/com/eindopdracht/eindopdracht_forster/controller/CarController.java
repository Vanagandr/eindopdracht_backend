package com.eindopdracht.eindopdracht_forster.controller;

import com.eindopdracht.eindopdracht_forster.dto.CarDto;
import com.eindopdracht.eindopdracht_forster.mapper.CarDtoMapper;
import com.eindopdracht.eindopdracht_forster.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CarDtoMapper carDtoMapper;

    public CarController(CarService carService, CarDtoMapper carDtoMapper) {
        this.carService = carService;
        this.carDtoMapper = carDtoMapper;
    }

    @PostMapping()
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto carDto) {
        CarDto car = carService.addCar(carDto);
        return ResponseEntity.ok(car);
    }

    @PostMapping("/inspectiondate")
    public ResponseEntity<CarDto> addInspectionDate(@RequestBody String carId, LocalDate inspectionDate){
        CarDto carDto = carService.addInspectionDate(carId, inspectionDate);
        return ResponseEntity.ok(carDto);
    }

    @PostMapping("/repairdate")
    public ResponseEntity<CarDto> addRepairDate(@RequestBody String carId, LocalDate repairDate){
        CarDto carDto = carService.addRepairDate(carId, repairDate);
        return ResponseEntity.ok(carDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> removeCar(@PathVariable String id){
        carService.removeCar(id);
        return ResponseEntity.ok("Auto is verwijderd!");
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<CarDto>> getCar(@PathVariable String id){
        Optional<CarDto> car = carService.getCar(id);
        return ResponseEntity.ok(car);
    }

    @PutMapping("/assigncustomer/{customerId}/{carId}")
    public ResponseEntity<String> assignCarToCustomer(@PathVariable Long customerId, @PathVariable String carId){
        String response = carService.assignCarToCustomer(customerId, carId);
        return ResponseEntity.ok(response);
    }


}
