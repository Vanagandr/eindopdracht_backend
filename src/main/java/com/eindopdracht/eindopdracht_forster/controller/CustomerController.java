package com.eindopdracht.eindopdracht_forster.controller;

import com.eindopdracht.eindopdracht_forster.dto.CustomerInputDto;
import com.eindopdracht.eindopdracht_forster.dto.CustomerOutputDto;
import com.eindopdracht.eindopdracht_forster.repository.CustomerRepository;
import com.eindopdracht.eindopdracht_forster.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
    }

    //Create a customer using the Dto, and checking if valid.
    @PostMapping
    public ResponseEntity<CustomerOutputDto> createCustomer(@Valid @RequestBody CustomerInputDto customerInputDto) {
        CustomerOutputDto createdCustomer = customerService.createCustomer(customerInputDto);
        return ResponseEntity.ok(createdCustomer);
    }
    // Getting a customer by the last name.
    @GetMapping
    public ResponseEntity<?> getCustomerByLastName(@RequestParam String lastName) {
        CustomerOutputDto customer = customerService.getCustomerByLastName(lastName);
        return ResponseEntity.ok(customer);
    }
    // Delete a customer.
    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestParam String lastName) {
        String responseMessage = customerService.deleteCustomer(lastName);
        return ResponseEntity.ok(responseMessage);
    }
}
