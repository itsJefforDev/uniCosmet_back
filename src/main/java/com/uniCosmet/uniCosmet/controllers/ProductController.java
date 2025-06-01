package com.uniCosmet.uniCosmet.controllers;

import com.uniCosmet.uniCosmet.model.Product;
import com.uniCosmet.uniCosmet.service.CloudinaryService;
import com.uniCosmet.uniCosmet.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Autowired
    private CloudinaryService cloudinaryService;


    // Obtener todos los productos
    @GetMapping
    @CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-c12a2.web.app/"})
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Obtener un producto por su ID
    @GetMapping("/{id}")
    @CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-c12a2.web.app/"})
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    // Crear un nuevo producto
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-c12a2.web.app/"})
    public ResponseEntity<?> createProduct(@RequestParam("name") String name,
                                           @RequestParam("description") String description,
                                           @RequestParam("price") Double price,
                                           @RequestParam("stock") Integer stock,
                                           @RequestParam("brand") String brand,
                                           @RequestParam("category") String category,
                                           @RequestParam("image") MultipartFile image) throws IOException {
        try {
            // Validar la imagen
            if (image.isEmpty() || !image.getContentType().startsWith("image/")) {
                return ResponseEntity.badRequest().body("Por favor sube un archivo de imagen válido");
            }

            // Subir imagen a Cloudinary
            String imageUrl = cloudinaryService.uploadFile(image);

            // Guardar producto con la URL de la imagen
            Product product = productService.saveProduct(name, description, price, stock, brand, category, imageUrl);

            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el producto: " + e.getMessage());
        }
    }

    // Crear un nuevo producto
    @PutMapping("/{id}")
    @CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-c12a2.web.app/"})
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestParam(value = "name",required = false) String name,
                                           @RequestParam(value ="description",required = false) String description,
                                           @RequestParam(value ="price",required = false) Double price,
                                           @RequestParam(value ="stock",required = false) Integer stock,
                                           @RequestParam(value ="brand",required = false) String brand,
                                           @RequestParam(value ="category",required = false) String category,
                                           @RequestParam(value ="image",required = false) MultipartFile image) throws IOException {
        try {
            String imageUrl = null;

            if (image != null && !image.isEmpty()) {
                // Validar la nueva imagen
                if (!image.getContentType().startsWith("image/")) {
                    return ResponseEntity.badRequest().body("Por favor sube un archivo de imagen válido");
                }

                // Eliminar la imagen anterior si existe
                Product existingProduct = productService.getProductById(id).orElse(null);
                if (existingProduct != null && existingProduct.getImage() != null) {
                    cloudinaryService.deleteFile(existingProduct.getImage());
                }

                // Subir la nueva imagen
                imageUrl = cloudinaryService.uploadFile(image);
            }

            // Actualizar el producto
            Product updatedProduct = productService.updateProduct(id, name, description, price, stock, brand, category, imageUrl);

            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el producto: " + e.getMessage());
        }
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    @CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-c12a2.web.app/"})
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {

        try {
            // Obtener el producto para eliminar su imagen
            Optional<Product> product = productService.getProductById(id);
            if (product.isPresent() && product.get().getImage() != null) {
                cloudinaryService.deleteFile(product.get().getImage());
            }

            // Eliminar el producto
            productService.deleteProductById(id);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Producto eliminado correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el producto: " + e.getMessage());
        }
    }

    // Endpoint para editar parcialmente un usuario
    @PatchMapping("/{id}")
    @CrossOrigin(origins = {"http://localhost:4200", "https://unicosmet-c12a2.web.app/"})
    public ResponseEntity<?> updateUserPatch(@PathVariable Long id, @RequestParam(value = "name",required = false) String name,
                                             @RequestParam(value ="description",required = false) String description,
                                             @RequestParam(value ="price",required = false) Double price,
                                             @RequestParam(value ="stock",required = false) Integer stock,
                                             @RequestParam(value ="brand",required = false) String brand,
                                             @RequestParam(value ="category",required = false) String category,
                                             @RequestParam(value ="image",required = false) MultipartFile image) throws IOException  {
        try {
            String imageUrl = null;

            if (image != null && !image.isEmpty()) {
                // Validar la nueva imagen
                if (!image.getContentType().startsWith("image/")) {
                    return ResponseEntity.badRequest().body("Por favor sube un archivo de imagen válido");
                }

                // Eliminar la imagen anterior si existe
                Product existingProduct = productService.getProductById(id).orElse(null);
                if (existingProduct != null && existingProduct.getImage() != null) {
                    cloudinaryService.deleteFile(existingProduct.getImage());
                }

                // Subir la nueva imagen
                imageUrl = cloudinaryService.uploadFile(image);
            }

            // Actualizar el producto
            Product updatedProduct = productService.updateProduct(id, name, description, price, stock, brand, category, imageUrl);

            return ResponseEntity.ok(updatedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el producto: " + e.getMessage());
        }
    }

}
