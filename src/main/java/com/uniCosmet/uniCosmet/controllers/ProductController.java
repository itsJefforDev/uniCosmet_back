package com.uniCosmet.uniCosmet.controllers;

import com.uniCosmet.uniCosmet.model.Product;
import com.uniCosmet.uniCosmet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

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
    public ResponseEntity<?> createProduct(@RequestParam("name") String name,
                                           @RequestParam("description") String description,
                                           @RequestParam("price") Double price,
                                           @RequestParam("stock") Integer stock,
                                           @RequestParam("brand") String brand,
                                           @RequestParam("category") String category,
                                           @RequestParam("image") MultipartFile image) throws IOException {
        try{
        return ResponseEntity.ok(productService.saveProduct(name, description, price, stock,brand, category, image));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el producto.");
        }
    }

    // Crear un nuevo producto
    @PutMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestParam(value = "name",required = false) String name,
                                           @RequestParam(value ="description",required = false) String description,
                                           @RequestParam(value ="price",required = false) Double price,
                                           @RequestParam(value ="stock",required = false) Integer stock,
                                           @RequestParam(value ="brand",required = false) String brand,
                                           @RequestParam(value ="category",required = false) String category,
                                           @RequestParam(value ="image",required = false) MultipartFile image) throws IOException {
        Product updatedProduct = productService.updateProduct(id,name, description, price, stock,brand,category, image);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto guardado exitosamente."+ updatedProduct); // Devuelve el usuario actualizado
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {

        productService.deleteProductById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "producto eliminado");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    // Endpoint para editar parcialmente un usuario
    @PatchMapping("/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> updateUserPatch(@PathVariable Long id, @RequestParam(value = "name",required = false) String name,
                                             @RequestParam(value ="description",required = false) String description,
                                             @RequestParam(value ="price",required = false) Double price,
                                             @RequestParam(value ="stock",required = false) Integer stock,
                                             @RequestParam(value ="brand",required = false) String brand,
                                             @RequestParam(value ="category",required = false) String category,
                                             @RequestParam(value ="image",required = false) MultipartFile image) throws IOException  {
        Product updatedProductPatch = productService.updateProductPatch(id,name, description, price, stock,brand,category, image);
        Map<String, String> response = new HashMap<>();
        response.put("message", "producto editado correctamente");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
