package com.eindopdracht.eindopdracht_forster.mapper;

import com.eindopdracht.eindopdracht_forster.dto.InvoiceDto;
import com.eindopdracht.eindopdracht_forster.model.Invoice;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDtoMapper {

    //from Invoice to InvoiceDto
    public InvoiceDto invoiceToDto(Invoice invoice) {
        InvoiceDto dto = new InvoiceDto();
        dto.id = invoice.getId();
        dto.car = invoice.getCar();
        dto.totalAmount = invoice.getTotalAmount();
        dto.invoicePaid = invoice.isInvoicePaid();

        return dto;
    }
}
