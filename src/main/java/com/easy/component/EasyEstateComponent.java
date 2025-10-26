package com.easy.component;

import com.easy.entity.PropertyTypeEntity;
import com.easy.response.PropertyTypeRequiredResponse;
import org.springframework.stereotype.Component;

@Component
public class EasyEstateComponent {

    public PropertyTypeRequiredResponse getRequireFieldsByPropertyId(PropertyTypeEntity propertyTypeEntity) {
        PropertyTypeRequiredResponse response = new PropertyTypeRequiredResponse();
        setCommonPropertyTypeRequirements(response);
        switch (propertyTypeEntity.getPropertyTypeCode()) {
            case "FARMING_LAND":
                response.setSoilType(true);
                break;

            case "OPEN_PLOT":
                response.setSoilType(true);
                break;

            case "INDIVIDUAL_HOUSE":
                response.setNumberOfFloors(true);
                response.setBuiltupArea(true);
                response.setConstructionYear(true);
                response.setHasLift(true);
                break;

            case "FLAT":
                response.setFlatNumber(true);
                response.setFlatNumber(true);
                response.setTotalFloors(true);
                response.setHasLift(true);
                response.setMaintenanceFee(true);
                break;
        }

        return response;
    }

    private void setCommonPropertyTypeRequirements(PropertyTypeRequiredResponse response) {
        response.setPropertyType(true);
        response.setPropertySize(true);
        response.setPropertyUnits(true);
        response.setListingType(true);
        response.setPrice(true);
        response.setCityName(true);
        response.setStateName(true);
        response.setCountryName(true);
        response.setAddress(true);
        response.setPostalCode(true);
        response.setLatitude(true);
        response.setLongitude(true);
        response.setAgentId(true);
        response.setOwnerName(true);
        response.setAreaUnit(true);
        response.setRoadAccess(true);
        response.setRoadWidth(true);
        response.setWaterSource(true);
    }
}
