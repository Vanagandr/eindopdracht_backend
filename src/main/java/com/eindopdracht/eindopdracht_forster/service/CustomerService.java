package com.eindopdracht.eindopdracht_forster.service;

import com.eindopdracht.eindopdracht_forster.dto.CustomerInputDto;
import com.eindopdracht.eindopdracht_forster.dto.CustomerOutputDto;
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
    public List<CustomerOutputDto> getCustomerByLastName(String lastName){
        List<Customer> customers = customerRepository.findByLastName(lastName);
        return customers.stream()
                .map(customerDtoMapper::customerOutputDtoMapper)
                .collect(Collectors.toList());
    }

    public CustomerOutputDto createCustomer(CustomerInputDto customerInputDto){
        Customer customer = customerDtoMapper.customerInputDtoMapper(customerInputDto);
        return customerDtoMapper.customerOutputDtoMapper(customerRepository.save(customer));
    }





}
