package com.eindopdracht.eindopdracht_forster.mapper;

import com.eindopdracht.eindopdracht_forster.model.Car;
import com.eindopdracht.eindopdracht_forster.dto.CarDto;
import org.springframework.stereotype.Component;


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
        dto.doneRepairs =car.getDoneRepairs();
        dto.usedParts = car.getUsedParts();
        dto.agreerepair=car.isAgreeRepair();

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
        car.setDoneRepairs(carDto.doneRepairs);
        car.setUsedParts(carDto.usedParts);
        car.setAgreeRepair(carDto.agreerepair);

        return car;

    }
}
