package com.josecarlos.tiendachapinita.repositories;

import com.josecarlos.tiendachapinita.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Optional<Customer> getCustomerByUsername(String username);

}
