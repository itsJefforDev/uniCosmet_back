package com.uniCosmet.uniCosmet.controllers;

import com.uniCosmet.uniCosmet.model.Product;
import com.uniCosmet.uniCosmet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    // Obtener todos los productos
    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Obtener un producto por su ID
    @GetMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Crear un nuevo producto
    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> createProduct(Product product) {
        try {
            // Convierte la imagen base64 a un byte[] y la asigna al producto
            if (product.getImage() != null) {
                byte[] imageBytes = Base64.getDecoder().decode(product.getImage());
                product.setImage(imageBytes);
            }
            productService.saveProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body("Producto guardado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el producto.");
        }
    }

    // Crear un nuevo producto
    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, Product product) {
        Product updatedProduct = productService.updateProduct(id,product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto guardado exitosamente."+ product); // Devuelve el usuario actualizado
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto eliminado exitosamente.");
    }
    // Endpoint para editar parcialmente un usuario
    @PatchMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> updateUserPatch(@PathVariable Long id, Product product) {
        Product updatedProductPatch = productService.updateProductPatch(id,product);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto editado exitosamente."+ product); // Devuelve el usuario actualizado
    }

}
