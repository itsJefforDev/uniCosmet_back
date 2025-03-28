package com.uniCosmet.uniCosmet.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PurchaseDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseDetailsId;

    @ManyToOne
    @JoinColumn(name = "purchaseId")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private Integer stock;
    private Double uniPrice;
    private Double subtotal;
}
