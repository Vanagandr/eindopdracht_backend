package com.eindopdracht.eindopdracht_forster.service;

import com.eindopdracht.eindopdracht_forster.dto.CustomerInputDto;
import com.eindopdracht.eindopdracht_forster.dto.CustomerOutputDto;
import com.eindopdracht.eindopdracht_forster.exception.CustomerNotFoundException;
import com.eindopdracht.eindopdracht_forster.exception.PhoneNumberNotUniqueException;
import com.eindopdracht.eindopdracht_forster.mapper.CustomerDtoMapper;
import com.eindopdracht.eindopdracht_forster.model.Customer;
import com.eindopdracht.eindopdracht_forster.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerDtoMapper customerDtoMapper;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test getCustomerByLastName
    @Test
    public void testGetCustomerByLastName() {
        String lastName = "Jansen";
        Customer customer = new Customer();
        customer.setLastName(lastName);
        CustomerOutputDto customerOutputDto = new CustomerOutputDto();

        when(customerRepository.findByLastName(lastName)).thenReturn(customer);
        when(customerDtoMapper.customerOutputDtoMapper(customer)).thenReturn(customerOutputDto);
        CustomerOutputDto result = customerService.getCustomerByLastName(lastName);

        assertEquals(result.lastName = "Jansen", lastName);
    }

    //Test Customer niet gevonden
    @Test
    public void testGetCustomerByLastName_CustomerNotFound() {

        String lastName = "Pieters";
        when(customerRepository.findByLastName(lastName)).thenReturn(null);
        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            customerService.getCustomerByLastName(lastName);
        });
        assertEquals("Geen klanten gevonden met de achternaam : Pieters", exception.getMessage());

    }

    // Test createCustomer
    @Test
    public void testCreateCustomer_Success() {
        CustomerInputDto customerInputDto = new CustomerInputDto();
        customerInputDto.phoneNumber = "0612345678";
        Customer customer = new Customer();
        CustomerOutputDto customerOutputDto = new CustomerOutputDto();

        when(customerRepository.findByPhoneNumber(customerInputDto.phoneNumber)).thenReturn(null);
        when(customerDtoMapper.customerInputDtoMapper(customerInputDto)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(customerDtoMapper.customerOutputDtoMapper(customer)).thenReturn(customerOutputDto);

        CustomerOutputDto result = customerService.createCustomer(customerInputDto);

        assertEquals(result.phoneNumber = "0612345678", customerInputDto.phoneNumber);

    }
    //Test telefoonnummer is niet uniek
    @Test
    public void testCreateCustomer_PhoneNumberNotUnique() {

        CustomerInputDto customerInputDto = new CustomerInputDto();
        customerInputDto.phoneNumber = "123456789";
        Customer existingCustomer = new Customer();

        when(customerRepository.findByPhoneNumber(customerInputDto.phoneNumber)).thenReturn(existingCustomer);


        PhoneNumberNotUniqueException exception = assertThrows(PhoneNumberNotUniqueException.class, () -> {
            customerService.createCustomer(customerInputDto);
        });
        assertEquals("Het telefoonnummer 123456789 bestaat al", exception.getMessage());
    }

    // Test  deleteCustomer
    @Test
    public void testDeleteCustomer_CustomerExists() {

        String lastName = "Smith";
        Customer customer = new Customer();
        customer.setLastName(lastName);

        when(customerRepository.findByLastName(lastName)).thenReturn(customer);


        String result = customerService.deleteCustomer(lastName);


        assertEquals("Klant is verwijderd", result);

    }

    @Test
    public void testDeleteCustomer_CustomerNotFound() {

        String lastName = "NonExistent";
        when(customerRepository.findByLastName(lastName)).thenReturn(null);


        CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class, () -> {
            customerService.deleteCustomer(lastName);
        });
        assertEquals("Deze klant is niet gevonden", exception.getMessage());

    }
}
