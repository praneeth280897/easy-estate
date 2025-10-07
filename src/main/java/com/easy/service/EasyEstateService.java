package com.easy.service;

import com.easy.request.PropertyTypeDTO;
import com.easy.request.SaveFormRequestDTO;
import com.easy.request.UnitDetailsDTO;
import org.springframework.http.ResponseEntity;

public interface EasyEstateService {

    ResponseEntity<String> saveForm(SaveFormRequestDTO saveFormRequestDTO);
}
