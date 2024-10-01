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

    private LocalDate inspectionDate;

    private LocalDate repairDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(LocalDate repairDate) {
        this.repairDate = repairDate;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }
    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

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


}
