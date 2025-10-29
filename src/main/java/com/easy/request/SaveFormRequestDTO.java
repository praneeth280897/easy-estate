package com.easy.request;

import lombok.Data;

import java.util.List;

@Data
public class SaveFormRequestDTO {

    private String propertyType;
    private Double areaOfProperty;
    private String listingType;
    private Double price;
    private String city;
    private String state;
    private String country;
    private String address;
    private Long postalCode;
    private Double latitude;
    private Double longitude;
    private Long agentId;
    private String ownerName;
    private String areaUnit;
    private Boolean roadAccess;
    private Long roadWidth;
    private String propertyFacing;
    private List<String> fileData;
    private Long length;
    private Long width;
    private String listedBy;

    //Flat
    public String flatNumber;
    public Long floorNumber;
    public Long totalFloors;
    public Boolean hasLift;
    public Double maintenanceFee;
    private Long noOfBathRooms;
    private Long noOfBedRooms;
    private Long noOfBalconies;

    // House
    public Long numberOfFloors;
    public Double builtUpArea;
    public Long constructionYear;
    private Long permittedFloors;
    private String furnishedStatus;
    private Boolean boundaryWall;
    private Double ageOfConstruction;
    private Double pricePerUnit;
    private Boolean isNegotiable;

    // Farming/Open Plot
    public String soilType;
    public String waterSource;

}
