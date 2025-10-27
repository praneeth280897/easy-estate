package com.easy.response;

import com.easy.request.UnitDetailsDTO;
import lombok.Data;

import java.util.List;

@Data
public class PropertyResponseDTO {

    private Long propetyId;
    private String propertyType;
    private Double propertySize;
    private UnitDetailsDTO propertyUnits;
    private String listingType;
    private Double price;
    private String cityName;
    private String stateName;
    private String countryName;
    private String address;
    private Long postalCode;
    private Double latitude;
    private Double longitude;
    private Long agentId;
    private String ownerName;
    private String areaUnit;
    private Boolean roadAccess;
    private Long roadWidth;

    //Flat
    public String flatNumber;
    public Integer floorNumber;
    public Integer totalFloors;
    public Boolean hasLift;
    public Double maintenanceFee;

    // House
    public Long numberOfFloors;
    public Double builtupArea;
    public Integer constructionYear;

    // Farming/Open Plot
    public String soilType;
    public String waterSource;

    private List<String> url;

}
