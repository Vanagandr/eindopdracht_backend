package com.eindopdracht.eindopdracht_forster.service;

import com.eindopdracht.eindopdracht_forster.exception.CarNotFoundException;
import com.eindopdracht.eindopdracht_forster.exception.InvoiceNotFoundException;
import com.eindopdracht.eindopdracht_forster.mapper.InvoiceDtoMapper;
import com.eindopdracht.eindopdracht_forster.repository.CarRepository;
import com.eindopdracht.eindopdracht_forster.repository.InvoiceRepository;
import org.springframework.stereotype.Service;
import com.eindopdracht.eindopdracht_forster.dto.InvoiceDto;
import com.eindopdracht.eindopdracht_forster.model.Car;
import com.eindopdracht.eindopdracht_forster.model.Invoice;

import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final CarRepository carRepository;
    private final InvoiceDtoMapper invoiceDtoMapper;

    public InvoiceService(InvoiceRepository invoiceRepository, CarRepository carRepository, InvoiceDtoMapper invoiceDtoMapper) {
        this.invoiceRepository = invoiceRepository;
        this.carRepository = carRepository;
        this.invoiceDtoMapper = invoiceDtoMapper;
    }

    public InvoiceDto createInvoice(String registration) {
        Car car = carRepository.findByRegistration(registration);
        if (car == null) {
            throw new CarNotFoundException("Deze auto is niet gevonden" );
        }

        Invoice invoice = new Invoice();
        invoice.setCar(car);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return invoiceDtoMapper.invoiceToDto(savedInvoice);
    }

    public String updateInvoicePaid(Long id, boolean paid) {
        Invoice invoice = invoiceRepository.findByInvoiceId(id);
        if (invoice == null){
            throw new InvoiceNotFoundException("Invoice is niet gevonden");
        }else{
            invoice.setInvoicePaid(paid);
            invoiceRepository.save(invoice);
            return "Invoice is op betaald gezet";
        }
    }

}

