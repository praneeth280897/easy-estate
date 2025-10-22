package com.easy.service;

import com.backblaze.b2.client.exceptions.B2Exception;
import com.easy.entity.PropertyDetailsEntity;
import com.easy.request.SaveFormRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface EasyEstateService {

    ResponseEntity<String> saveForm(SaveFormRequestDTO saveFormRequestDTO);

    ResponseEntity<List<PropertyDetailsEntity>> getPropertyDetails();

    String uploadVideo(MultipartFile file, Long propertyId) throws IOException, B2Exception;

    ResponseEntity<List<String>> getFileToOpen(String propertyId);
}
