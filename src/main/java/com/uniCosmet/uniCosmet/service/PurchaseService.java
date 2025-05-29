package com.uniCosmet.uniCosmet.service;

import com.uniCosmet.uniCosmet.dto.PurchaseDTO;
import com.uniCosmet.uniCosmet.model.Product;
import com.uniCosmet.uniCosmet.model.Purchase;
import com.uniCosmet.uniCosmet.model.User;
import com.uniCosmet.uniCosmet.repository.ProductRepository;
import com.uniCosmet.uniCosmet.repository.PurchaseRepository;
import com.uniCosmet.uniCosmet.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public PurchaseService(PurchaseRepository purchaseRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.purchaseRepository = purchaseRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Purchase createPurchase(PurchaseDTO purchaseDTO, String username) {
        User user = userRepository.findByNickname(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        Product product = productRepository.findById(purchaseDTO.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if(product.getStock() < purchaseDTO.getQuantity()) {
            throw new RuntimeException("Stock insuficiente");
        }

        // Actualizar stock
        product.setStock(product.getStock() - purchaseDTO.getQuantity());
        productRepository.save(product);

        Purchase purchase = new Purchase(null,product,LocalDateTime.now(),purchaseDTO.getQuantity(),(product.getPrice() * purchaseDTO.getQuantity()),user);
        purchase.setUser(user);
        purchase.setProduct(product);
        purchase.setQuantity(purchaseDTO.getQuantity());
        purchase.setPurchaseDate(LocalDateTime.now());
        purchase.setTotalPrice(product.getPrice() * purchaseDTO.getQuantity());

        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getUserPurchases(String username) {
        User user = userRepository.findByNickname(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        return purchaseRepository.findByUser(user);
    }
}
