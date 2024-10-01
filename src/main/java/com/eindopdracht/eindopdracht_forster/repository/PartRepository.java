package com.eindopdracht.eindopdracht_forster.repository;

import com.eindopdracht.eindopdracht_forster.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part, String> {
    Part findByType(String type);
}
