package com.eindopdracht.eindopdracht_forster.service;

import com.eindopdracht.eindopdracht_forster.dto.CustomerInputDto;
import com.eindopdracht.eindopdracht_forster.dto.CustomerOutputDto;
import com.eindopdracht.eindopdracht_forster.exception.CustomerNotFoundException;
import com.eindopdracht.eindopdracht_forster.exception.PhoneNumberNotUniqueException;
import com.eindopdracht.eindopdracht_forster.mapper.CustomerDtoMapper;
import com.eindopdracht.eindopdracht_forster.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import com.eindopdracht.eindopdracht_forster.model.Customer;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoMapper customerDtoMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoMapper customerDtoMapper) {
        this.customerRepository = customerRepository;
        this.customerDtoMapper = customerDtoMapper;
    }

    //Find Customers by last name
    public List<CustomerOutputDto> getCustomerByLastName(String lastName) {
        List<Customer> customers = customerRepository.findByLastName(lastName);
        if (customers.isEmpty()) {
            throw new CustomerNotFoundException("Geen klanten gevonden met de achternaam : " + lastName);
        }
        return customers.stream()
                .map(customerDtoMapper::customerOutputDtoMapper)
                .collect(Collectors.toList());
    }

    public CustomerOutputDto createCustomer(CustomerInputDto customerInputDto) {
        if (customerRepository.findByPhoneNumber(customerInputDto.phoneNumber) != null) {
            throw new PhoneNumberNotUniqueException("Het telefoonnummer " + customerInputDto.phoneNumber + " bestaat al");
        }
        Customer customer = customerDtoMapper.customerInputDtoMapper(customerInputDto);
        return customerDtoMapper.customerOutputDtoMapper(customerRepository.save(customer));
        }


    }

