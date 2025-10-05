package com.easy.entity;


import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Flat")
@Data
public class Flat extends PropertyDetailsEntity {

    private String flatNumber;
    private Integer floorNumber;
    private Integer totalFloors;
    private Boolean hasLift;
    private Double maintenanceFee;

}

