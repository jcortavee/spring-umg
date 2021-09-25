package com.josecarlos.tiendachapinita.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.josecarlos.tiendachapinita.models.Invoice;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "invoice_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    @JsonIgnore
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private Integer quantity;

    private Double price;

}
