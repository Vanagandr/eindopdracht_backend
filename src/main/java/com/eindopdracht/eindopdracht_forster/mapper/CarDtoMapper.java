package com.eindopdracht.eindopdracht_forster.mapper;

import com.eindopdracht.eindopdracht_forster.model.Car;
import com.eindopdracht.eindopdracht_forster.dto.CarDto;
import org.springframework.stereotype.Component;

@Component
public class CarDtoMapper {


    //Map from Car to CarDto
    public CarDto carToDtoMapper(Car car) {
        CarDto dto = new CarDto();
        dto.registration = car.getRegistration();
        dto.brand = car.getBrand();
        dto.yearOfConstruct = car.getYearOfConstruct();
        dto.inspectionDate = car.getInspectionDate();
        dto.repairDate = car.getRepairDate();

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

        return car;

    }
}
