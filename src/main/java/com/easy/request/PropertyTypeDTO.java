package com.easy.request;

import lombok.Data;

@Data
public class PropertyTypeDTO {

    private Long id;
    private String propCode;
    private String propType;
    private String displayText;
    private String measurementType;
}
