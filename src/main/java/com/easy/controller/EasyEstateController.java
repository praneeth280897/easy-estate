package com.easy.controller;

import com.easy.request.PropertyTypeDTO;
import com.easy.request.SaveFormRequestDTO;
import com.easy.request.UnitDetailsDTO;
import com.easy.response.PropertyResponseDTO;
import com.easy.service.EasyEstateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private ResponseEntity<PropertyResponseDTO> getPropertyDetails() {
        return null;
    }

}
