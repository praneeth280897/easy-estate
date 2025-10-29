package com.easy.response;

import lombok.Data;

@Data
public class PropertyTypeRequiredResponse {

    private Boolean propertyType;
    private Boolean propertySize;
    private Boolean propertyUnits;
    private Boolean listingType;
    private Boolean price;
    private Boolean cityName;
    private Boolean stateName;
    private Boolean countryName;
    private Boolean address;
    private Boolean postalCode;
    private Boolean latitude;
    private Boolean longitude;
    private Boolean agentId;
    private Boolean ownerName;
    private Boolean areaUnit;
    private Boolean roadAccess;
    private Boolean roadWidth;
    private Boolean constructionYear;
    private Boolean permittedFloors;
    private Boolean boundaryWall;
    private Boolean ageOfConstruction;
    private Boolean pricePerUnit;
    private Boolean isNegotiable;
    private Boolean length;
    private Boolean width;
    private Boolean furnishedStatus;
    private Boolean isPriceNegotiable;
    private Boolean listedBy;

    //Flat
    private Boolean flatNumber;
    private Boolean floorNumber;
    private Boolean totalFloors;
    private Boolean hasLift;
    private Boolean maintenanceFee;
    private Boolean noOfBedRooms;
    private Boolean noOfBathRooms;
    private Boolean noOfWarendas;


    // House
    private Boolean numberOfFloors;
    private Boolean builtUpArea;

    // Farming/Open Plot
    public Boolean soilType;
    public Boolean waterSource;

    private Boolean url;

}
