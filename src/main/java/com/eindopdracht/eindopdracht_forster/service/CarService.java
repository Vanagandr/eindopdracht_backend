package com.eindopdracht.eindopdracht_forster.service;

import com.eindopdracht.eindopdracht_forster.dto.CarDto;
import com.eindopdracht.eindopdracht_forster.exception.CarNotFoundException;
import com.eindopdracht.eindopdracht_forster.exception.CustomerNotFoundException;
import com.eindopdracht.eindopdracht_forster.exception.PartNotFoundException;
import com.eindopdracht.eindopdracht_forster.exception.RepairNotFoundException;
import com.eindopdracht.eindopdracht_forster.mapper.CarDtoMapper;
import com.eindopdracht.eindopdracht_forster.model.*;
import com.eindopdracht.eindopdracht_forster.repository.CarRepository;
import com.eindopdracht.eindopdracht_forster.repository.CustomerRepository;
import com.eindopdracht.eindopdracht_forster.repository.PartRepository;
import com.eindopdracht.eindopdracht_forster.repository.RepairRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarDtoMapper carDtoMapper;
    private final CustomerRepository customerRepository;
    private final RepairRepository repairRepository;
    private final PartRepository partRepository;



    public CarService(CarRepository carRepository, CarDtoMapper carDtoMapper, CustomerRepository customerRepository, RepairRepository repairRepository, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.carDtoMapper = carDtoMapper;
        this.customerRepository = customerRepository;
        this.repairRepository = repairRepository;
        this.partRepository = partRepository;

    }

    public CarDto addCar(CarDto carDto) {
        Car car = carDtoMapper.carDtoToCarMapper(carDto);
        Car savedCar = carRepository.save(car);
        return carDtoMapper.carToDtoMapper(savedCar);
    }

    public String removeCar(String registration) {
        Optional<Car> car = carRepository.findById(registration);
        if (car.isPresent()) {
            carRepository.deleteById(registration);
            return "Auto is verwijderd!";
        }
        throw new CarNotFoundException("Deze auto is niet gevonden!");
    }

    public Optional<CarDto> getCar(String registration) {
        Optional<Car> car = carRepository.findById(registration);
        if (car.isPresent()) {
            return car.map(carDtoMapper::carToDtoMapper);
        }
        throw new CarNotFoundException("Deze auto is niet gevonden!");


    }

    public CarDto addInspectionDate(String registration, LocalDate inspectiondate) {
        Car car = carRepository.findById(registration).orElseThrow(() ->
                new CarNotFoundException("Deze auto is niet gevonden!"));
        car.setInspectionDate(inspectiondate);
        carRepository.save(car);
        return carDtoMapper.carToDtoMapper(car);
    }


    public CarDto addRepairDate(String registration, LocalDate repairdate) {
        Car car = carRepository.findById(registration).orElseThrow(() ->
                new CarNotFoundException("Deze auto is niet gevonden!"));
        car.setRepairDate(repairdate);
        carRepository.save(car);
        return carDtoMapper.carToDtoMapper(car);
    }

    public String assignCarToCustomer(Long customerId, String registration){
        Customer customer = customerRepository.findById(customerId);
        if (customer == null) {
            throw new CustomerNotFoundException("Deze klant is niet gevonden!");
        }
        Car car = carRepository.findByRegistration(registration);
        if (car == null) {
            throw new CarNotFoundException("Deze auto is niet gevonden!");
        }
        car.setCustomer(customer);
        carRepository.save(car);

        return "Auto met kenteken " + registration + " is gekoppeld aan klant " + customer.getLastName();
    }

    public CarDto addNeededRepair(String registration, String type) {
        Car car = carRepository.findByRegistration(registration);
        if (car == null) {
            throw new CarNotFoundException("Auto niet gevonden");
        }
        Repair repair = repairRepository.findByType(type);
        if (repair == null) {
            throw new RepairNotFoundException("Reparatiehandeling niet gevonden");
        }
        car.getNeededRepairs().add(repair);
        carRepository.save(car);
        return carDtoMapper.carToDtoMapper(car);
    }

    public CarDto addDoneRepair(String registration, String type) {
        Car car = carRepository.findByRegistration(registration);
        if (car == null) {
            throw new CarNotFoundException("Auto niet gevonden");
        }
        Repair repair = repairRepository.findByType(type);
        if (repair == null) {
            throw new RepairNotFoundException("Reparatiehandeling niet gevonden");
        }
        car.getDoneRepairs().add(repair);
        carRepository.save(car);
        return carDtoMapper.carToDtoMapper(car);
    }


    public CarDto addUsedPart(String registration, String type) {
        Car car = carRepository.findByRegistration(registration);
        if (car == null) {
            throw new CarNotFoundException("Auto niet gevonden");
        }
        Part part = partRepository.findByType(type);
        if (part == null) {
            throw new PartNotFoundException("Onderdeel niet gevonden");
        }

        car.getUsedParts().add(part);
        carRepository.save(car);
        return carDtoMapper.carToDtoMapper(car);
    }

    public String updateAgreeRepair(String registration, boolean agreeRepair){
        Car car = carRepository.findByRegistration(registration);
        if (car == null) {
            throw new CarNotFoundException("Auto niet gevonden");
        }else{
            car.setAgreeRepair(agreeRepair);
            carRepository.save(car);

            return "Status voor reparatie van auto " + registration + " is nu : " + agreeRepair;
        }
    }

    @Transactional
    public Car addCarPapers(String registration, CarPapers carPapers) {
        Optional<Car> optionalCar = carRepository.findById(registration);
        if (optionalCar.isEmpty()) {
            throw new CarNotFoundException("Auto niet gevonden");
        }

        Car car = optionalCar.get();
        car.setCarPapers(carPapers);
        return carRepository.save(car);
    }

    @Transactional
    public CarPapers getCarPapers(String registration ){
        Optional<Car> optionalCar = carRepository.findById(registration);
        if (optionalCar.isEmpty()) {
            throw new CarNotFoundException("Auto niet gevonden");
        }
        return optionalCar.get().getCarPapers();
    }
}
