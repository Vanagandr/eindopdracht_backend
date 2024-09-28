package com.eindopdracht.eindopdracht_forster.mapper;

import com.eindopdracht.eindopdracht_forster.dto.CustomerOutputDto;
import org.springframework.stereotype.Component;
import com.eindopdracht.eindopdracht_forster.dto.CustomerInputDto;
import com.eindopdracht.eindopdracht_forster.model.Customer;

@Component
public class CustomerDtoMapper {

    //OutputDto mapper
    public CustomerOutputDto customerOutputDtoMapper (Customer customer) {
        CustomerOutputDto dto = new CustomerOutputDto();
        dto.lastName = customer.getLastName();
        dto.city = customer.getCity();
        dto.phoneNumber = customer.getPhoneNumber();
        return dto;
    }

        //InputDto to Entity
        public Customer customerInputDtoMapper(CustomerInputDto dto) {
            Customer customer = new Customer();
            customer.setLastName(dto.lastName);
            customer.setCity(dto.city);
            customer.setPhoneNumber(dto.phoneNumber);
            return customer;
    }
}
