package com.eindopdracht.eindopdracht_forster.model;

import jakarta.persistence.*;

@Entity
@Table(name="parts")
public class Part {

    @Id
    @Column(nullable = false, unique = true)
    private String type;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
