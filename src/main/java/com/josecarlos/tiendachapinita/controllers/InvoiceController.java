package com.josecarlos.tiendachapinita.controllers;

import com.josecarlos.tiendachapinita.models.Invoice;
import com.josecarlos.tiendachapinita.repositories.InvoiceDetailRepository;
import com.josecarlos.tiendachapinita.repositories.InvoiceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceDetailRepository invoiceDetailRepository;

    public InvoiceController(InvoiceRepository invoiceRepository, InvoiceDetailRepository invoiceDetailRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceDetailRepository = invoiceDetailRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Invoice>> getInvoices() {
        return new ResponseEntity<>(invoiceRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable("id") Long id) {
        return new ResponseEntity<>(invoiceRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Invoice> saveInvoice(@RequestBody Invoice invoice) {
        Invoice entitySaved = invoiceRepository.save(invoice);
        invoice.getInvoiceDetails().forEach(invoiceDetail -> {
            invoiceDetail.setInvoice(entitySaved);
            invoiceDetailRepository.save(invoiceDetail);
        });
        return new ResponseEntity<>(entitySaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") Long id, @RequestBody Invoice invoice) {
        Invoice entity = invoiceRepository.findById(id).get();
        BeanUtils.copyProperties(invoice, entity);

        return new ResponseEntity<>(invoiceRepository.save(entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteInvoice(@PathVariable("id") Long id) {
        Invoice entity = invoiceRepository.findById(id).get();
        invoiceRepository.delete(entity);

        return new ResponseEntity(HttpStatus.OK);
    }

}
