package com.easy.request;

import lombok.Data;

import java.util.List;

@Data
public class SaveFormRequestDTO {

    private String propertyType;
    private Double areaOfProperty;
    private String listingType;
    private double price;
    private String city;
    private String state;
    private String country;
    private String address;
    private Long postalCode;
    private double latitude;
    private double longitude;
    private Long agentId;
    private String ownerName;
    private String areaUnit;
    private boolean roadAccess;
    private Long roadWidth;
    private String propertyFacing;
    private List<String> fileData;
    private Long length;
    private long width;
    private String listedBy;

    //Flat
    public String flatNumber;
    public Integer floorNumber;
    public Integer totalFloors;
    public Boolean hasLift;
    public Double maintenanceFee;
    private Long noOfBathRooms;
    private Long noOfBedRooms;
    private Long noOfBalconies;

    // House
    public Integer numberOfFloors;
    public Double builtUpArea;
    public Integer constructionYear;
    private Long permittedFloors;
    private String FurnishedStatus;
    private boolean boundaryWall;
    private double ageOfConstruction;
    private Double pricePerUnit;
    private boolean isNegotiable;

    // Farming/Open Plot
    public String soilType;
    public String waterSource;

}
