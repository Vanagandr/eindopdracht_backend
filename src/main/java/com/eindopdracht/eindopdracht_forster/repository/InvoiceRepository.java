package com.eindopdracht.eindopdracht_forster.repository;

import com.eindopdracht.eindopdracht_forster.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Invoice findByInvoiceId(long id);}