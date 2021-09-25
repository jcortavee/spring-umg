package com.josecarlos.tiendachapinita.controllers;

import com.josecarlos.tiendachapinita.models.Product;
import com.josecarlos.tiendachapinita.repositories.ProductRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Product>> getProducts() {
        return new ResponseEntity<>(productRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {
        return new ResponseEntity<>(productRepository.findById(id).get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productRepository.save(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product entity = productRepository.findById(id).get();
        BeanUtils.copyProperties(product, entity);

        return new ResponseEntity<>(productRepository.save(entity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        Product entity = productRepository.findById(id).get();
        productRepository.delete(entity);

        return new ResponseEntity(HttpStatus.OK);
    }

}
