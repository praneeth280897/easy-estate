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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private ResponseEntity<List<PropertyDetailsEntity>> getPropertyDetails() {
        return easyEstateService.getPropertyDetails();
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadVideo(@RequestParam("file") MultipartFile file) {
        try {
            B2FileVersion response = easyEstateService.uploadVideo(file);
            return ResponseEntity.ok("Uploaded: " + response.getFileName() + " | File ID: " + response.getFileId());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

}
