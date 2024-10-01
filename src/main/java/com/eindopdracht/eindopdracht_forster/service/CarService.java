package com.eindopdracht.eindopdracht_forster.service;

import com.eindopdracht.eindopdracht_forster.dto.CarDto;
import com.eindopdracht.eindopdracht_forster.dto.InspectionDto;
import com.eindopdracht.eindopdracht_forster.exception.CarNotFoundException;
import com.eindopdracht.eindopdracht_forster.mapper.CarDtoMapper;
import com.eindopdracht.eindopdracht_forster.model.Car;
import com.eindopdracht.eindopdracht_forster.repository.CarRepository;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarDtoMapper carDtoMapper;

    public CarService(CarRepository carRepository, CarDtoMapper carDtoMapper) {
        this.carRepository = carRepository;
        this.carDtoMapper = carDtoMapper;
    }

    public CarDto addCar(CarDto carDto) {
        Car car = carDtoMapper.carDtoToCarMapper(carDto);
        Car savedCar = carRepository.save(car);
        return carDtoMapper.carToDtoMapper(savedCar);
    }

    public String removeCar(String carId) {
        Optional<Car> car = carRepository.findById(carId);
        if (car.isPresent()) {
            carRepository.deleteById(carId);
            return "Auto is verwijderd!";
        }
        throw new CarNotFoundException("Deze auto is niet gevonden!");
    }

    public Optional<CarDto> getCar(String carId) {
        Optional<Car> car = carRepository.findById(carId);
        if (car.isPresent()) {
            return car.map(carDtoMapper::carToDtoMapper);
        }
        throw new CarNotFoundException("Deze auto is niet gevonden!");


    }
    public CarDto addInspectionDate (String carId, LocalDate inspectiondate) {
        Car car = carRepository.findById(carId).orElseThrow(()->
                new CarNotFoundException("Deze auto is niet gevonden!"));
        car.setInspectionDate(inspectiondate);
        carRepository.save(car);
        return carDtoMapper.carToDtoMapper(car);
    }




    public CarDto addRepairDate (String carId, LocalDate repairdate){
        Car car = carRepository.findById(carId).orElseThrow(() ->
                new CarNotFoundException("Deze auto is niet gevonden!"));
        car.setRepairDate(repairdate);
        carRepository.save(car);
        return carDtoMapper.carToDtoMapper(car);
        }


    }


