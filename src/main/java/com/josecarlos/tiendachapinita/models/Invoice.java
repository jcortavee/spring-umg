package com.josecarlos.tiendachapinita.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "invoice")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_number")
    private int invoiceNumber;


    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.EAGER)
    private Set<InvoiceDetail> invoiceDetails;
}
