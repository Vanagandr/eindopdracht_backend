package com.eindopdracht.eindopdracht_forster.mapper;

import com.eindopdracht.eindopdracht_forster.model.Car;
import com.eindopdracht.eindopdracht_forster.dto.CarDto;
import com.eindopdracht.eindopdracht_forster.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarDtoMapper {


    private final CustomerDtoMapper customerDtoMapper;

    public CarDtoMapper(CustomerDtoMapper customerDtoMapper) {
        this.customerDtoMapper = customerDtoMapper;
    }

    //Map from Car to CarDto
    public CarDto carToDtoMapper(Car car) {
        CarDto dto = new CarDto();
        dto.registration = car.getRegistration();
        dto.brand = car.getBrand();
        dto.yearOfConstruct = car.getYearOfConstruct();
        dto.inspectionDate = car.getInspectionDate();
        dto.repairDate = car.getRepairDate();
        if (car.getCustomer() != null) {
            dto.customer = customerDtoMapper.customerOutputDtoMapper(car.getCustomer());
        }else{
            dto.customer = null;
        }
        dto.neededRepairs =car.getNeededRepairs();
        return dto;
    }

    //Map from CarDto to Car
    public Car carDtoToCarMapper(CarDto carDto) {
        Car car = new Car();
        car.setRegistration(carDto.registration);
        car.setBrand(carDto.brand);
        car.setYearOfConstruct(carDto.yearOfConstruct);
        car.setInspectionDate(carDto.inspectionDate);
        car.setRepairDate(carDto.repairDate);
        car.setNeededRepairs(carDto.neededRepairs);

        return car;

    }
}
