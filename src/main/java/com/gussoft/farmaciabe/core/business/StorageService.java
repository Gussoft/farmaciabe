package com.gussoft.farmaciabe.core.business;

import com.gussoft.farmaciabe.core.exception.FileException;
import java.io.IOException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    void init() throws IOException;

    String store(MultipartFile file, String prefix) throws FileException;

    Resource loadAsResource(String filename) throws FileException;

    void deleteImage(String filename) throws FileException;

}
