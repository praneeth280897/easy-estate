package com.easy.entity;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Data
@DiscriminatorValue("Farming Land")
public class FarmingLand extends PropertyDetailsEntity {

    private String soilType;
    private String waterSource;

}
