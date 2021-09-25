package com.josecarlos.tiendachapinita.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String nit;
    private String phone;

    private String username;
    private String password;

    @OneToMany(mappedBy = "customer")
    private Set<Invoice> invoices;

}
