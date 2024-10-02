package com.eindopdracht.eindopdracht_forster.controller;

import com.eindopdracht.eindopdracht_forster.dto.InvoiceDto;
import com.eindopdracht.eindopdracht_forster.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/{registration}")
    public ResponseEntity<InvoiceDto> createInvoice(@PathVariable String registration) {
        InvoiceDto invoiceDto = invoiceService.createInvoice(registration);
        return new ResponseEntity<>(invoiceDto, HttpStatus.CREATED);
    }
}