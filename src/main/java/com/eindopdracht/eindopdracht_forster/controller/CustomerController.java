package com.eindopdracht.eindopdracht_forster.controller;

import com.eindopdracht.eindopdracht_forster.dto.CustomerInputDto;
import com.eindopdracht.eindopdracht_forster.dto.CustomerOutputDto;
import com.eindopdracht.eindopdracht_forster.repository.CustomerRepository;
import com.eindopdracht.eindopdracht_forster.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.eindopdracht.eindopdracht_forster.model.Customer;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    //PostMapping to Create Customer using Dto's
    @PostMapping
    public ResponseEntity<CustomerOutputDto> createCustomer(@Valid @RequestBody CustomerInputDto customerInputDto) {
        CustomerOutputDto createdCustomer = customerService.createCustomer(customerInputDto);
        return ResponseEntity.ok(createdCustomer);
    }

    @GetMapping
    public ResponseEntity<?> getCustomerByLastName(@RequestParam String lastName) {
        List<CustomerOutputDto> customers = customerService.getCustomerByLastName(lastName);
        if (customers.isEmpty()) {
            return ResponseEntity.status(204).body("Er zijn geen klanten gevonden met achternaam " + lastName);
        }
        return ResponseEntity.ok(customers);
    }
}
