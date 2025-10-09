package com.easy.service;

import com.easy.entity.PropertyDetailsEntity;
import com.easy.request.PropertyTypeDTO;
import com.easy.request.SaveFormRequestDTO;
import com.easy.request.UnitDetailsDTO;
import com.easy.response.PropertyResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EasyEstateService {

    ResponseEntity<String> saveForm(SaveFormRequestDTO saveFormRequestDTO);

    ResponseEntity<List<PropertyDetailsEntity>> getPropertyDetails();
}
