package com.easy.service;

import com.backblaze.b2.client.exceptions.B2Exception;
import com.easy.entity.PropertyDetailsEntity;
import com.easy.request.SaveFormRequestDTO;
import com.easy.response.PropertyResponseDTO;
import com.easy.response.PropertyTypeRequiredResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface EasyEstateService {

    ResponseEntity<String> saveForm(SaveFormRequestDTO saveFormRequestDTO) throws B2Exception;

    ResponseEntity<List<PropertyResponseDTO>> getPropertyDetails();

    String uploadVideo(MultipartFile file, Long propertyId) throws IOException, B2Exception;

    List<String> getFileToOpen(String propertyId);

    PropertyResponseDTO getPropertyDetailsById(Long propertyId);

    ResponseEntity<PropertyTypeRequiredResponse> getRequireFieldsByPropertyId(Long propertyId) throws Exception;
}
