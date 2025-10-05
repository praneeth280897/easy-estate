package com.easy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "tbl_image_details")
public class ImageDetailsEntity extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="name")
    private String name;

}
