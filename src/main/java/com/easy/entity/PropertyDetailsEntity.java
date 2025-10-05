package com.easy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@Table(name = "tbl_property_details")
@DiscriminatorColumn(name = "property_type", discriminatorType = DiscriminatorType.STRING)
public abstract class PropertyDetailsEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "property_size")
    private Double propertSize;

    @Column(name = "area_unit")
    private String areaUnit;

    @Column(name = "listing_type")
    private String listingType;

    @Column(name = "price")
    private Double price;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "address")
    private String address;

    @Column(name = "postalCode")
    private Long postalCode;

    @Column(name = "lat")
    private double latitude;

    @Column(name = "lon")
    private double longitude;

    @Column(name = "agent_id")
    private Long agentId;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "road_access")
    private boolean roadAccess;

    @Column(name = "road_width")
    private Long roadWidth;

    @Column(name = "property_facing")
    private String propertyFacing;

}

