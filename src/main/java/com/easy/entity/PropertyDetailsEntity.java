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

    //flat
    @Column(name="flat_number")
    private String flatNumber;
    @Column(name = "floor_number")
    private Long floorNumber;
    @Column(name = "total_floors")
    private Long totalFloors;
    @Column(name="has_lift")
    private Boolean hasLift;
    @Column(name = "maintenance_fee")
    private Double maintenanceFee;
    @Column(name = "total_bed_rooms")
    private Long totalBedRooms;
    @Column(name = "total_bath_rooms")
    private Long toalBathRooms;
    @Column(name = "total_warandas")
    private Long totalWarendas;

    @Column(name="length")
    private Long length;
    @Column(name = "width")
    private Long width;
    @Column(name = "age_of_construction")
    private Double ageOfConstruction;
    @Column(name = "price_per_unit")
    private Double pricePerUnit;
    @Column(name = "boundary_wall")
    private Boolean isBoundaryWall;
    @Column(name = "is_negotiable")
    private Boolean isPriceNegotiable;
    @Column(name = "permitted_floors")
    private Long permittedFloors;
    @Column(name = "furnished_status")
    private String furnishedStatus;
    @Column(name = "listed_by")
    private String listedBy;



    //IndividualHouse
    @Column(name = "number_of_floors")
    private Long numberOfFloors;
    @Column(name = "builtup_area")
    private Double builtupArea;
    @Column(name = "construction_year")
    private Long constructionYear;

    //FarmingLand
    @Column(name = "soil_type")
    private String soilType;
    @Column(name="water_source")
    private String waterSource;

}

