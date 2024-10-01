package com.eindopdracht.eindopdracht_forster.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @Column(nullable = false, unique = true)
    private String registration;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String yearOfConstruct;

    @Column(nullable = false)
    private LocalDate inspectDay;

    @Column(nullable = false)
    private LocalDate repairDay;

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getYearOfConstruct() {
        return yearOfConstruct;
    }

    public void setYearOfConstruct(String yearOfConstruct) {
        this.yearOfConstruct = yearOfConstruct;
    }

    public LocalDate getInspectDay() {
        return inspectDay;
    }

    public void setInspectDay(LocalDate inspectDay) {
        this.inspectDay = inspectDay;
    }

    public LocalDate getRepairDay() {
        return repairDay;
    }

    public void setRepairDay(LocalDate repairDay) {
        this.repairDay = repairDay;
    }

}
