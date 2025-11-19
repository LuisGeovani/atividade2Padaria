package com.webacademy.padaria.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

public class FileSystemStorageService implements IStorageService {
    
    private final Path publicRootLocation;
    private final Path privateRootLocation;

    public FileSystemStorageService(Path publicRootLocation, Path privateRootLocation) {
        this.publicRootLocation = publicRootLocation;
        this.privateRootLocation = privateRootLocation;
    }

    public void init() {
        try {
            Files.createDirectories(privateRootLocation);
            Files.createDirectories(publicRootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível inicializar o armazenamento", e);
        }
    }

    private Path getRootLocation(boolean isPublic) {
        return isPublic ? this.publicRootLocation : this.privateRootLocation;
    }

    public String store(MultipartFile file, boolean isPublic) {
        try {
            if (file == null || file.isEmpty()) {
                throw new RuntimeException("Falha ao salvar arquivo vazio.");
            }

            String originalFileName = file.getOriginalFilename();
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            
            String uniqueFileName = UUID.randomUUID().toString() + extension;

            Path root = getRootLocation(isPublic);

            Path destinationFile = root.resolve(Paths.get(uniqueFileName))
                .normalize().toAbsolutePath();
            
            if (!destinationFile.getParent().equals(root.toAbsolutePath())) {
                throw new RuntimeException("Não é possível salvar o arquivo fora do diretório.");    
            }

            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }

            return uniqueFileName;
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar arquivo", e);
        }
    }

    private Path load(String fileName, boolean isPublic) {
        return getRootLocation(isPublic).resolve(fileName);
    }

    public void delete(String fileName, boolean isPublic) {
        try {
            Path file = load(fileName, isPublic);
            Files.deleteIfExists(file);
        } catch(IOException e) {
            throw new RuntimeException("Falha ao excluir arquivo: " + fileName, e);
        }
    }

    @Override
    public Resource loadAsResource(String fileName, boolean isPublic) {
        try {
            Path file = load(fileName, isPublic);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Não foi possível ler o arquivo: " + fileName);
            }
        } catch(MalformedURLException e) {
            throw new RuntimeException("Não foi possível ler o arquivo: " + fileName);
        }
    }
}
