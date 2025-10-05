package com.easy.entity;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Open Plot")
@Data
public class OpenPlot extends PropertyDetailsEntity {

    private String soilType;
    private String waterSource;

}

