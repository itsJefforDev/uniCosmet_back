package com.uniCosmet.uniCosmet.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadFile(MultipartFile file) throws IOException {
        try {
            File uploadedFile = convertMultiPartToFile(file);
            Map uploadResult = cloudinary.uploader().upload(uploadedFile,
                    ObjectUtils.asMap("folder", "productos"));
            boolean deleted = uploadedFile.delete();

            if (!deleted) {
                throw new IOException("No se pudo eliminar el archivo temporal");
            }

            return (String) uploadResult.get("secure_url");
        } catch (Exception e) {
            throw new IOException("Error al subir imagen a Cloudinary: " + e.getMessage(), e);
        }
    }

    public void deleteFile(String imageUrl) throws IOException {
        try {
            String publicId = extractPublicIdFromUrl(imageUrl);
            if (publicId != null) {
                cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            }
        } catch (Exception e) {
            throw new IOException("Error al eliminar imagen de Cloudinary: " + e.getMessage(), e);
        }
    }

    private String extractPublicIdFromUrl(String url) {
        if (url == null || url.isEmpty()) return null;

        try {
            // Extraer el public_id de diferentes formatos de URL de Cloudinary
            String[] parts = url.split("/upload/");
            if (parts.length < 2) return null;

            String path = parts[1];
            // Eliminar parámetros de transformación si existen
            path = path.split("\\.")[0]; // Quitar extensión
            path = path.split("/v\\d+/")[path.split("/v\\d+/").length - 1]; // Quitar versión

            return "products/" + path; // Añadir el folder para asegurar la ruta correcta
        } catch (Exception e) {
            throw new IllegalArgumentException("URL de Cloudinary no válida: " + url);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = File.createTempFile("temp-", file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(file.getBytes());
        }
        return convFile;
    }

}
