package com.josecarlos.tiendachapinita.repositories;

import com.josecarlos.tiendachapinita.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
