package com.uniCosmet.uniCosmet.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;

    private Double total;

    @OneToMany(mappedBy = "purchase")
    private List<PurchaseDetails> purchaseDetailsList;
}
