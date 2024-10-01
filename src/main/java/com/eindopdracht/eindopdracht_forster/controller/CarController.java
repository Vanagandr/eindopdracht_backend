package com.eindopdracht.eindopdracht_forster.controller;

import com.eindopdracht.eindopdracht_forster.dto.CarDto;
import com.eindopdracht.eindopdracht_forster.mapper.CarDtoMapper;
import com.eindopdracht.eindopdracht_forster.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
