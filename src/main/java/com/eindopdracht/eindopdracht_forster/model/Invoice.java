package com.eindopdracht.eindopdracht_forster.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "invoices")
//Voorkomt het maken van een endless loop bij het creeren van een invoice.
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_registration", nullable = false)
    private Car car;

    private double totalAmount;

    //-------------------------------------------------------

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
        this.totalAmount = calculateTotalAmount();
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    private double calculateTotalAmount() {
        double total = 0;

        if (car != null) {
            total += car.getDoneRepairs().stream()
                    .mapToDouble(Repair::getPrice) // Assuming Repair has a getPrice() method
                    .sum();

            total += car.getUsedParts().stream()
                    .mapToDouble(Part::getPrice) // Assuming Part has a getPrice() method
                    .sum();
        }
        return total;
    }
}
