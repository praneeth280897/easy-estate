package com.easy.entity;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("Individual House")
public class IndividualHouse extends PropertyDetailsEntity {

    private Integer numberOfFloors;
    private Double builtupArea;
    private Integer constructionYear;

}

