package com.uniCosmet.uniCosmet.controllers;

import com.uniCosmet.uniCosmet.model.Product;
import com.uniCosmet.uniCosmet.model.User;
import com.uniCosmet.uniCosmet.service.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")

public class ProductController {
    @Autowired
    private ProductSevice productSevice;

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productSevice.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Obtener un producto por su ID
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productSevice.getProductById(id);
    }

    // Crear un nuevo producto
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productSevice.saveProduct(product);
    }

    // Crear un nuevo producto
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productSevice.updateProduct(id, product);
        return ResponseEntity.ok(updatedProduct); // Devuelve el usuario actualizado
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productSevice.deleteProductById(id);
    }
    // Endpoint para editar parcialmente un usuario
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateUserPatch(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productSevice.updateProductPatch(id, product);
        return ResponseEntity.ok(updatedProduct); // Devuelve el usuario actualizado
    }

}
