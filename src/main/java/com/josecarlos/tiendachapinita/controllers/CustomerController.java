package com.josecarlos.tiendachapinita.controllers;

import com.josecarlos.tiendachapinita.models.Customer;
import com.josecarlos.tiendachapinita.repositories.CustomerRepository;
import com.josecarlos.tiendachapinita.services.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    public CustomerController(CustomerRepository customerRepository, CustomerService customerService) {
        this.customerRepository = customerRepository;
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Customer>> getCustomers() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") Long id) {
        return new ResponseEntity<>(customerRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.createCustomer(customer), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        Customer entity = customerRepository.findById(id).get();
        BeanUtils.copyProperties(customer, entity);

        return new ResponseEntity<>(customerRepository.save(entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable("id") Long id) {
        Customer entity = customerRepository.findById(id).get();
        customerRepository.delete(entity);

        return new ResponseEntity(HttpStatus.OK);
    }

}
