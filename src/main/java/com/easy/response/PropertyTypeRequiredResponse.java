package com.easy.response;

import lombok.Data;

@Data
public class PropertyTypeRequiredResponse {

    private boolean propertyType;
    private boolean propertySize;
    private boolean propertyUnits;
    private boolean listingType;
    private boolean price;
    private boolean cityName;
    private boolean stateName;
    private boolean countryName;
    private boolean address;
    private boolean postalCode;
    private boolean latitude;
    private boolean longitude;
    private boolean agentId;
    private boolean ownerName;
    private boolean areaUnit;
    private boolean roadAccess;
    private boolean roadWidth;

    //Flat
    public boolean flatNumber;
    public boolean floorNumber;
    public boolean totalFloors;
    public boolean hasLift;
    public boolean maintenanceFee;

    // House
    public boolean numberOfFloors;
    public boolean builtupArea;
    public boolean constructionYear;

    // Farming/Open Plot
    public boolean soilType;
    public boolean waterSource;

    private boolean url;

}
