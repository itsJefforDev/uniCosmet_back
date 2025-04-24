package com.uniCosmet.uniCosmet.service;

import com.uniCosmet.uniCosmet.model.Product;
import com.uniCosmet.uniCosmet.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private ProductRepository productRepository;

    // Obtener todos los productos
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Obtener un producto por su ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // Guardar un producto
    public Product saveProduct(String name, String description,Double price, Integer stock, MultipartFile image) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setImage(image.getBytes());

        return productRepository.save(product);
    }

    // Eliminar un producto por su ID
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    // Metodo para editar un product
    public Product updateProduct(Long id, Product product){
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualizar los campos del usuario
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStock(product.getStock());
        existingProduct.setImage(product.getImage());

        return productRepository.save(existingProduct);
    }

    // Metodo para editar parcialmente un usuario
    public Product updateProductPatch(Long id, Product product) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Solo actualizamos los campos que no sean nulos en el objeto updatedUser
        if (product.getName() != null) {
            existingProduct.setName(product.getName());
        }
        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }
        if (product.getDescription() != null) {
            existingProduct.setDescription(product.getDescription());
        }
        if (product.getStock() != null) {
            existingProduct.setStock(product.getStock());
        }
        if (product.getImage() != null) {
            existingProduct.setImage(product.getImage());
        }


        return productRepository.save(existingProduct);

    }
}
