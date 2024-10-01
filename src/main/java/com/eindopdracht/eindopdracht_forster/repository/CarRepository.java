package com.eindopdracht.eindopdracht_forster.repository;
import com.eindopdracht.eindopdracht_forster.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, String> {
    Car findByRegistration(String registration);
}
