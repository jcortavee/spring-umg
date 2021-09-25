package com.josecarlos.tiendachapinita.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer stock;

    private Double price;

}
