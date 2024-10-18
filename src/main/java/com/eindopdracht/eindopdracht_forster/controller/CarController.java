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
    //Add a car
    @PostMapping()
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto carDto) {
        CarDto car = carService.addCar(carDto);
        return ResponseEntity.ok(car);
    }
    //Add an inspection date
    @PostMapping("/inspectiondate/{registration}/{inspectionDate}")
    public ResponseEntity<Optional<CarDto>> addInspectionDate(@PathVariable String registration, @PathVariable LocalDate inspectionDate){
        Optional<CarDto> carDto = carService.addInspectionDate(registration, inspectionDate);
        return ResponseEntity.ok(carDto);
    }
    //Add a repair date.
    @PostMapping("/repairdate/{registration}/{repairDate}")
    public ResponseEntity<Optional<CarDto>> addRepairDate(@PathVariable String registration,@PathVariable LocalDate repairDate){
        Optional<CarDto> carDto = carService.addRepairDate(registration, repairDate);
        return ResponseEntity.ok(carDto);
    }
    //Delete car by registration
    @DeleteMapping("{registration}")
    public ResponseEntity<String> removeCar(@PathVariable String registration){
        carService.removeCar(registration);
        return ResponseEntity.ok("Auto is verwijderd!");
    }
    //Get a car by registration
    @GetMapping("{registration}")
    public ResponseEntity<Optional<CarDto>> getCar(@PathVariable String registration){
        Optional<CarDto> car = carService.getCar(registration);
        return ResponseEntity.ok(car);
    }
    //Assign a car to a customer
    @PostMapping("/assigncustomer/{customerId}/{carId}")
    public ResponseEntity<String> assignCarToCustomer(@PathVariable Long customerId, @PathVariable String carId){
        String response = carService.assignCarToCustomer(customerId, carId);
        return ResponseEntity.ok(response);
    }
    //Add a needed repair to a car
    @PostMapping("/neededrepairs/{type}/{registration}")
    public ResponseEntity<CarDto> addNeededRepair(@PathVariable String registration, @PathVariable String type){
        CarDto carDto = carService.addNeededRepair(registration, type);
        return ResponseEntity.ok(carDto);
    }
    // Add a done repair to a car
    @PostMapping("/donerepairs/{type}/{registration}")
    public ResponseEntity<CarDto> addDoneRepair(@PathVariable String registration, @PathVariable String type){
        CarDto carDto = carService.addDoneRepair(registration, type);
        return ResponseEntity.ok(carDto);
    }
    // Add a used part to a car
    @PostMapping("/usedparts/{type}/{registration}")
    public ResponseEntity<CarDto> addUsedPart(@PathVariable String registration, @PathVariable String type){
        CarDto carDto = carService.addUsedPart(registration, type);
        return ResponseEntity.ok(carDto);
    }
    // Update the repair agreement
    @PostMapping("/{registration}/repair")
    public ResponseEntity<String> updateRepairStatus(@PathVariable String registration, @RequestParam boolean agreeRepair) {
        String response = carService.updateAgreeRepair(registration, agreeRepair);
        return ResponseEntity.ok(response);
    }
    // Add papers to a car
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
    // Get papers from a car
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

