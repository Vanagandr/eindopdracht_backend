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
    public CustomerOutputDto getCustomerByLastName(String lastName) {
        Customer customer = customerRepository.findByLastName(lastName);
        if (customer == null) {
            throw new CustomerNotFoundException("Geen klanten gevonden met de achternaam : " + lastName);
        }
        return customerDtoMapper.customerOutputDtoMapper(customer);
    }

    //Create Customers
    public CustomerOutputDto createCustomer(CustomerInputDto customerInputDto) {
        if (customerRepository.findByPhoneNumber(customerInputDto.phoneNumber) != null) {
            throw new PhoneNumberNotUniqueException("Het telefoonnummer " + customerInputDto.phoneNumber + " bestaat al");
        }
        Customer customer = customerDtoMapper.customerInputDtoMapper(customerInputDto);
        return customerDtoMapper.customerOutputDtoMapper(customerRepository.save(customer));
    }

    //Delete Customers
    public String deleteCustomer(String lastName) {
        Customer customer = customerRepository.findByLastName(lastName);
        if (customer != null) {
            customerRepository.delete(customer);
            return ("Klant is verwijderd");
        }else{
            throw new CustomerNotFoundException("Deze klant is niet gevonden");
        }

    }
}
