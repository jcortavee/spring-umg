package com.josecarlos.tiendachapinita.repositories;


import com.josecarlos.tiendachapinita.models.Invoice;
import org.springframework.data.repository.CrudRepository;

public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
