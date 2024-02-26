package com.gussoft.farmaciabe.core.business.impl;

import com.gussoft.farmaciabe.core.business.StorageService;
import com.gussoft.farmaciabe.core.exception.FileException;
import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

    @Value("${media.location}")
    private String mediaLocation;

    private Path rootLocation;

    @Override
    @PostConstruct
    public void init() throws IOException {
        rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
    }

    @Override
    public String store(MultipartFile file, String prefix) throws FileException {

        if (!file.isEmpty()) {
            try {
                String filename = String.format("%s-%s%s", prefix, UUID.randomUUID(),
                        Objects.requireNonNull(file.getOriginalFilename()).replace(" ", ""));
                Path destinationFile = rootLocation.resolve(Paths.get(filename))
                        .normalize().toAbsolutePath();
                try (InputStream inputStream = file.getInputStream()) {
                    Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                }
                return filename;
            } catch (IOException e) {
                throw new FileException("Failed to store file : " + e.getMessage());
            }
        }
        throw new FileException("File is empty");
    }

    @Override
    public Resource loadAsResource(String filename) throws FileException {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileException(String.format("Could not read file : %s", filename));
            }
        } catch (MalformedURLException e) {
            throw new FileException("Could not read file : " + filename + e.getMessage());
        }
    }

    @Override
    public void deleteImage(String filename) throws FileException {
        File file = new File(rootLocation.getFileName() + "\\" + filename);
        if (!file.exists()) {
            System.err.printf("Could not read file : %s%n", filename);
        }
        file.delete();
    }

}
