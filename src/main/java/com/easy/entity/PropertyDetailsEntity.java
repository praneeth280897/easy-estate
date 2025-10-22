package com.easy.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Data
@Table(name = "tbl_property_details")
public class PropertyDetailsEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "property_size")
    private Double propertSize;

    @Column(name="property_type")
    private String propertyType;

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

    private String flatNumber;
    private Integer floorNumber;
    private Integer totalFloors;
    private Boolean hasLift;
    private Double maintenanceFee;

    private Integer numberOfFloors;
    private Double builtupArea;
    private Integer constructionYear;

    private String soilType;
    private String waterSource;

}

