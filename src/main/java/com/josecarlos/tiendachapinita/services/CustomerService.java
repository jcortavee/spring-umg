package com.josecarlos.tiendachapinita.services;

import com.josecarlos.tiendachapinita.models.Customer;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CustomerService extends UserDetailsService {

    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long customerId, Customer customer);
    void deleteCustomer(Long customerId);
    boolean customerExists(Customer customer);
    Customer getCustomer(String username);
    Customer getCustomer(Long customerId);
    List<Customer> getCustomers();

}
