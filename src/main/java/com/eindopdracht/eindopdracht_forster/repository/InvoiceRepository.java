package com.eindopdracht.eindopdracht_forster.repository;

import com.eindopdracht.eindopdracht_forster.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
