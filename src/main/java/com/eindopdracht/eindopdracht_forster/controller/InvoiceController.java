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
    // Endpoint to create a invoice.
    @PostMapping("/create")
    public ResponseEntity<InvoiceDto> createInvoice(@RequestParam String registration) {
        InvoiceDto invoiceDto = invoiceService.createInvoice(registration);
        return new ResponseEntity<>(invoiceDto, HttpStatus.CREATED);
    }
    // Set a  invoice to paid.
    @PostMapping("/paid/{id}")
    public ResponseEntity<String> updateInvoicePaid(@PathVariable Long id, @RequestParam boolean invoicePaid) {
        String response = invoiceService.updateInvoicePaid(id, invoicePaid);
        return ResponseEntity.ok(response);
    }
}