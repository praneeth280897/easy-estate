package com.easy.service;

import com.backblaze.b2.client.structures.B2FileVersion;
import com.easy.entity.PropertyDetailsEntity;
import com.easy.request.SaveFormRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EasyEstateService {

    ResponseEntity<String> saveForm(SaveFormRequestDTO saveFormRequestDTO);

    ResponseEntity<List<PropertyDetailsEntity>> getPropertyDetails();

    B2FileVersion uploadVideo(MultipartFile file) throws IOException;
}
