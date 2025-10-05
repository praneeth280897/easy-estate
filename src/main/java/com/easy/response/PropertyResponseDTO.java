package com.easy.response;

import com.easy.request.UnitDetailsDTO;
import lombok.Data;

@Data
public class PropertyResponseDTO {

    private String propertyType;
    private Double propertySize;
    private UnitDetailsDTO propertyUnits;
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

    //Flat
    public String flatNumber;
    public Integer floorNumber;
    public Integer totalFloors;
    public Boolean hasLift;
    public Double maintenanceFee;

    // House
    public Integer numberOfFloors;
    public Double builtupArea;
    public Integer constructionYear;

    // Farming/Open Plot
    public String soilType;
    public String waterSource;

}
