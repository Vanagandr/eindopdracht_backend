package com.eindopdracht.eindopdracht_forster.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
//Voorkomt het maken van een endless loop bij het creeren van een invoice.
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "registration")
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car")
    private List<Invoice> invoices = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_repairs")
    private List<Repair> neededRepairs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_repairs_done")
    private List<Repair> doneRepairs = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_parts_used")
    private List<Part> usedParts = new ArrayList<>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private boolean agreeRepair;

    //----------------------------------------------


    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public boolean isAgreeRepair() {
        return agreeRepair;
    }

    public void setAgreeRepair(boolean agreeRepair) {
        this.agreeRepair = agreeRepair;
    }

    public List<Part> getUsedParts() {
        return usedParts;
    }

    public void setUsedParts(List<Part> usedParts) {
        this.usedParts = usedParts;
    }

    public List<Repair> getNeededRepairs() {
        return neededRepairs;
    }

    public void setNeededRepairs(List<Repair> neededRepairs) {
        this.neededRepairs = neededRepairs;
    }

    public List<Repair> getDoneRepairs() {
        return doneRepairs;
    }

    public void setDoneRepairs(List<Repair> doneRepairs) {
        this.doneRepairs = doneRepairs;
    }

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
