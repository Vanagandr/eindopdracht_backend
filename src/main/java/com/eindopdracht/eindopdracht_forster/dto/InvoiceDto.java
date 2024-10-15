package com.eindopdracht.eindopdracht_forster.dto;

import com.eindopdracht.eindopdracht_forster.model.Car;

public class InvoiceDto {

    public Long id;

    public Car car;

    public double totalAmount;

    public boolean invoicePaid;
}
