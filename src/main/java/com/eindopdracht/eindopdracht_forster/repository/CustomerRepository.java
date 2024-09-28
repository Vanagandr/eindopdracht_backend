package com.eindopdracht.eindopdracht_forster.repository;

import com.eindopdracht.eindopdracht_forster.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    List<Customer> findByLastName(String lastName);
    Customer findByPhoneNumber(String phoneNumber);
}
