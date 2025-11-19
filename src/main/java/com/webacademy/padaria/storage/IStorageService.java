package com.webacademy.padaria.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
    void init();
    String store(MultipartFile file, boolean isPublic);
    Resource loadAsResource(String fileName, boolean isPublic);
    void delete(String fileName, boolean isPublic);
}
