package com.easy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tbl_prop_type")
@Data
public class PropertyTypeEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prop_type")
    private String propType;

    @Column(name = "prop_code")
    private String propCode;

    @Column(name = "disp_text")
    private String displayText;

    @Column(name = "msr_type")
    private String measurementType;

}
