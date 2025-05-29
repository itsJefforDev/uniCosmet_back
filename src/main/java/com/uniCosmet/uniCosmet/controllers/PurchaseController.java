package com.uniCosmet.uniCosmet.controllers;

import com.uniCosmet.uniCosmet.dto.PurchaseDTO;
import com.uniCosmet.uniCosmet.model.Purchase;
import com.uniCosmet.uniCosmet.model.PurchaseResponseDTO;
import com.uniCosmet.uniCosmet.security.JwtService;
import com.uniCosmet.uniCosmet.service.PurchaseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/purchases")
@CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-c12a2.web.app/"})
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final JwtService jwtService;

    public PurchaseController(PurchaseService purchaseService, JwtService jwtService) {
        this.purchaseService = purchaseService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<PurchaseResponseDTO> createPurchase(
            @RequestBody PurchaseDTO purchaseDTO,
            @RequestHeader("Authorization") String token) {

        String username = jwtService.extractUsername(token.substring(7));
        Purchase purchase = purchaseService.createPurchase(purchaseDTO, username);

        PurchaseResponseDTO response = convertToResponseDTO(purchase);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/my-purchases")
    public ResponseEntity<List<PurchaseResponseDTO>> getUserPurchases(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {

        String username = jwtService.extractUsername(token.substring(7));
        List<Purchase> purchases = purchaseService.getUserPurchases(username);

        List<PurchaseResponseDTO> response = purchases.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    private PurchaseResponseDTO convertToResponseDTO(Purchase purchase) {
        PurchaseResponseDTO dto = new PurchaseResponseDTO();
        dto.setId(purchase.getId());
        dto.setProductName(purchase.getProduct().getName());
        dto.setProductPrice(purchase.getProduct().getPrice());
        dto.setQuantity(purchase.getQuantity());
        dto.setTotalPrice(purchase.getTotalPrice());
        dto.setPurchaseDate(purchase.getPurchaseDate());
        return dto;
    }

}
