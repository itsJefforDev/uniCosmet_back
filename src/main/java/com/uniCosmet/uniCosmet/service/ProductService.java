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
    public Product saveProduct(String name, String description,Double price, Integer stock, String brand, String category, String image) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setBrand(brand);
        product.setCategory(category);
        product.setImage(image);

        return productRepository.save(product);
    }

    // Eliminar un producto por su ID
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    // Metodo para editar un product
    public Product updateProduct(Long id, String name, String description,Double price, Integer stock,String brand, String category, String image) throws IOException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualizar los campos del usuario
        existingProduct.setName(name);
        existingProduct.setDescription(description);
        existingProduct.setPrice(price);
        existingProduct.setStock(stock);
        existingProduct.setBrand(brand);
        existingProduct.setCategory(category);
        existingProduct.setImage(image);

        return productRepository.save(existingProduct);
    }

    // Metodo para editar parcialmente un usuario
    public Product updateProductPatch(Long id, String name, String description,Double price, Integer stock,String brand, String category, String image) throws IOException{
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        System.out.println(name);
        System.out.println(description);

        // Solo actualizamos los campos que no sean nulos en el objeto updatedUser
        if (name != null && !name.isEmpty()) {
            existingProduct.setName(name);
        }
        if (price != null) {
            existingProduct.setPrice(price);
        }
        if (description != null && !description.isEmpty()) {
            existingProduct.setDescription(description);
        }
        if (stock != null) {
            existingProduct.setStock(stock);
        }
        if (brand != null && !brand.isEmpty()) {
            existingProduct.setBrand(brand);
        }
        if (category != null && !category.isEmpty()) {
            existingProduct.setCategory(category);
        }
        if (image != null && !image.isEmpty()) {
            existingProduct.setImage(image);
        }


        return productRepository.save(existingProduct);

    }
}
