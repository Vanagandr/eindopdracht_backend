package com.eindopdracht.eindopdracht_forster.controller;

import com.eindopdracht.eindopdracht_forster.dto.CarDto;
import com.eindopdracht.eindopdracht_forster.mapper.CarDtoMapper;
import com.eindopdracht.eindopdracht_forster.model.Car;
import com.eindopdracht.eindopdracht_forster.model.CarPapers;
import com.eindopdracht.eindopdracht_forster.service.CarPapersService;
import com.eindopdracht.eindopdracht_forster.service.CarService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;
    private final CarDtoMapper carDtoMapper;
    private final CarPapersService carPapersService;

    public CarController(CarService carService, CarDtoMapper carDtoMapper, CarPapersService carPapersService) {
        this.carService = carService;
        this.carDtoMapper = carDtoMapper;
        this.carPapersService = carPapersService;
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

    @PostMapping("/assigncustomer/{customerId}/{carId}")
    public ResponseEntity<String> assignCarToCustomer(@PathVariable Long customerId, @PathVariable String carId){
        String response = carService.assignCarToCustomer(customerId, carId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/neededrepairs/{type}/{registration}")
    public ResponseEntity<CarDto> addNeededRepair(@PathVariable String registration, @PathVariable String type){
        CarDto carDto = carService.addNeededRepair(registration, type);
        return ResponseEntity.ok(carDto);
    }

    @PostMapping("/donerepairs/{type}/{registration}")
    public ResponseEntity<CarDto> addDoneRepair(@PathVariable String registration, @PathVariable String type){
        CarDto carDto = carService.addDoneRepair(registration, type);
        return ResponseEntity.ok(carDto);
    }

    @PostMapping("/usedparts/{type}/{registration}")
    public ResponseEntity<CarDto> addUsedPart(@PathVariable String registration, @PathVariable String type){
        CarDto carDto = carService.addUsedPart(registration, type);
        return ResponseEntity.ok(carDto);
    }

    @PostMapping("/{carId}/repair")
    public ResponseEntity<String> updateRepairStatus(@PathVariable String carId, @RequestParam boolean agreeRepair) {
        String response = carService.updateAgreeRepair(carId, agreeRepair);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{registration}/papers")
    public ResponseEntity<Car> addPapers(@PathVariable String registration, @RequestBody MultipartFile file) throws IOException {
        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/cars/")
                .path(Objects.requireNonNull(registration.toString()))
                .path("/papers")
                .toUriString();

        CarPapers carpapers = carPapersService.storeFile(file,url);

        Car car = carService.addCarPapers(registration, carpapers);

        return ResponseEntity.created(URI.create(url)).body(car);

    }

    @GetMapping("/{registration}/papers")
    public ResponseEntity<byte[]> getCarPapers(@PathVariable String registration) {
        CarPapers carPapers = carService.getCarPapers(registration);
        MediaType mediaType;

        try {
            mediaType = MediaType.parseMediaType(carPapers.getContentType());
        }catch (InvalidMediaTypeException ignore){
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }

        return ResponseEntity
                .ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION,"inline;fileName=" + carPapers.getTitle())
                .body(carPapers.getContents());

        }

    }

