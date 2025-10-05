package com.easy.request;

import lombok.Data;

@Data
public class UnitDetailsDTO {

    private Long id;
    private String unitCode;
    private String unitType;
    private String displayText;
    private PropertyTypeDTO propertyTypeDTO;
}
