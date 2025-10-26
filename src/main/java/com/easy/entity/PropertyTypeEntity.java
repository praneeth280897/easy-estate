package com.easy.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_property_type")
@Data
public class PropertyTypeEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "property_type_name")
    private String propertyTypeName;

    @Column(name = "property_type_code")
    private String propertyTypeCode;

}
