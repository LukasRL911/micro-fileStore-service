package com.davinci.filestore.service;

import com.davinci.filestore.model.FileMetadata;
import com.davinci.filestore.repository.FileMetadataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;

@Service
public class FileStorageService {

    private final FileMetadataRepository repository;

    @Value("${file.upload-dir:uploads}")
    private String uploadDir;

    public FileStorageService(FileMetadataRepository repository) {
        this.repository = repository;
    }

    public FileMetadata storeFile(MultipartFile file) throws IOException {
        System.out.println("📦 Recibiendo archivo...");
        System.out.println("Nombre: " + file.getOriginalFilename());
        System.out.println("Tipo: " + file.getContentType());
        System.out.println("Tamaño: " + file.getSize());

        if (file.isEmpty()) {
            System.out.println("⚠️ El archivo está vacío");
            throw new IllegalArgumentException("Archivo vacío");
        }

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
            System.out.println("📁 Carpeta creada: " + uploadPath.toAbsolutePath());
        }

        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("✅ Archivo guardado en: " + filePath.toAbsolutePath());

        FileMetadata metadata = new FileMetadata();
        metadata.setFileName(fileName);
        metadata.setFileType(file.getContentType());
        metadata.setFilePath(filePath.toString());

        FileMetadata saved = repository.save(metadata);
        System.out.println("🗂️ Metadatos guardados con ID: " + saved.getId());

        return saved;
    }

    public List<FileMetadata> getAllFiles() {
        return repository.findAll();
    }

    public Resource loadFileAsResource(Long id) {
        Optional<FileMetadata> optional = repository.findById(id);
        if (optional.isEmpty()) throw new RuntimeException("Archivo no encontrado");

        Path path = Paths.get(optional.get().getFilePath());
        return new FileSystemResource(path);
    }

    public void deleteFile(Long id) {
        Optional<FileMetadata> optional = repository.findById(id);
        if (optional.isEmpty()) throw new RuntimeException("Archivo no encontrado");

        Path path = Paths.get(optional.get().getFilePath());
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException("Error al eliminar archivo");
        }

        repository.deleteById(id);
    }

    public FileMetadata updateMetadata(Long id, FileMetadata updated) {
        Optional<FileMetadata> optional = repository.findById(id);
        if (optional.isEmpty()) throw new RuntimeException("Archivo no encontrado");

        FileMetadata existing = optional.get();
        existing.setFileName(updated.getFileName());
        existing.setFileType(updated.getFileType());
        existing.setFilePath(updated.getFilePath());

        return repository.save(existing);
    }
}
