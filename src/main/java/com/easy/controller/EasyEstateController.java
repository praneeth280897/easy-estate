package com.easy.controller;

import com.backblaze.b2.client.structures.B2FileVersion;
import com.easy.entity.PropertyDetailsEntity;
import com.easy.request.PropertyTypeDTO;
import com.easy.request.SaveFormRequestDTO;
import com.easy.request.UnitDetailsDTO;
import com.easy.response.PropertyResponseDTO;
import com.easy.service.EasyEstateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/property")
public class EasyEstateController {

    @Autowired
    private EasyEstateService easyEstateService;

    @PostMapping("/save/form")
    public ResponseEntity<String> saveFormDetails(@RequestBody SaveFormRequestDTO saveFormRequestDTO) {
        return easyEstateService.saveForm(saveFormRequestDTO);
    }


    @GetMapping("/get/property/details")
    private ResponseEntity<List<PropertyResponseDTO>> getPropertyDetails() {
        return easyEstateService.getPropertyDetails();
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestParam("file") MultipartFile file, @RequestParam("propertyId") Long propertyId) {
        try {
            String response = easyEstateService.uploadVideo(file, propertyId);
            return ResponseEntity.ok("Uploaded file URL to Access:" + response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }


    @GetMapping("/get-file-to-open")
    public ResponseEntity<List<String>> getFileToOpen(@RequestParam ("propertyId") String propertyId) {
        return new ResponseEntity<>(easyEstateService.getFileToOpen(propertyId), HttpStatus.OK);
    }

    @GetMapping("/get-property-detils-by-id")
    public ResponseEntity<PropertyResponseDTO> getPropertyDetailsById(@RequestParam ("propertyId") Long propertyId){
        return new ResponseEntity<>(easyEstateService.getPropertyDetailsById(propertyId),HttpStatus.OK);
    }

}
