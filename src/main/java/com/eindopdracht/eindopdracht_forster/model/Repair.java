package com.eindopdracht.eindopdracht_forster.model;

import jakarta.persistence.*;

@Entity
@Table(name ="repairs")
public class Repair {

    @Id
    @Column(nullable = false, unique = true)
    private String type;

    @Column(nullable = false)
    private double price;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne
    @JoinColumn(name = "car_registration")
    private Car car;
}
