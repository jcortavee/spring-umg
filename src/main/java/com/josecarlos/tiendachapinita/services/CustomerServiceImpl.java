package com.josecarlos.tiendachapinita.services;

import com.josecarlos.tiendachapinita.models.Customer;
import com.josecarlos.tiendachapinita.repositories.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerServiceImpl(CustomerRepository customerRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRepository = customerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Customer createCustomer(Customer customer) {

        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));

        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customer) {

        Optional<Customer> customerSaved = customerRepository.findById(customerId);

        if (!customerSaved.isPresent()) {
            throw new RuntimeException("El cliente no existe");
        }

        Customer updatedCustomer = customerSaved.get();

        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
        BeanUtils.copyProperties(customer, updatedCustomer);

        return customerRepository.save(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Optional<Customer> customerSaved = customerRepository.findById(customerId);

        if (!customerSaved.isPresent()) {
            throw new RuntimeException("El cliente no existe");
        }

        customerRepository.delete(customerSaved.get());
    }

    public boolean customerExists(Customer customer) {
        Optional<Customer> customerSaved = customerRepository.findById(customer.getId());
        return customerSaved.isPresent();
    }

    @Override
    public Customer getCustomer(Long customerId) {
        Optional<Customer> customerSaved = customerRepository.findById(customerId);
        if (!customerSaved.isPresent()) {
            throw new RuntimeException("El cliente no existe");
        }

        return customerSaved.get();
    }


    @Override
    public List<Customer> getCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Customer> customer = customerRepository.getCustomerByUsername(s);

        if (!customer.isPresent()) {
            throw new UsernameNotFoundException(s);
        }

        return new org.springframework.security.core.userdetails.User(customer.get().getUsername(),
                customer.get().getPassword(), new ArrayList<>());
    }

    @Override
    public Customer getCustomer(String username) {
        Optional<Customer> user = customerRepository.getCustomerByUsername(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException(username);
        }

        return user.get();
    }
}
