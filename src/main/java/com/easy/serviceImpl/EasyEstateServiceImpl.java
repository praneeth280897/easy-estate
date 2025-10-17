package com.easy.serviceImpl;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.contentSources.B2ByteArrayContentSource;
import com.backblaze.b2.client.contentSources.B2ContentSource;
import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.client.structures.B2Bucket;
import com.backblaze.b2.client.structures.B2FileVersion;
import com.backblaze.b2.client.structures.B2UploadFileRequest;
import com.easy.entity.*;
import com.easy.repository.PropertyDetailsRepository;
import com.easy.request.SaveFormRequestDTO;
import com.easy.service.EasyEstateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
@Slf4j
public class EasyEstateServiceImpl implements EasyEstateService {

    @Autowired
    private PropertyDetailsRepository propertyDetailsRepository;

    @Autowired
    private B2StorageClient client;

    @Autowired
    private B2Bucket bucket;
    @Override
    public ResponseEntity<String> saveForm(SaveFormRequestDTO saveFormRequestDTO) {
        PropertyDetailsEntity property;
        switch (saveFormRequestDTO.getPropertyType()) {
            case "Flat":
                property = setFlatDetailsToSave(saveFormRequestDTO);
                break;

            case "Individual House":
                property = setInvidualHouse(saveFormRequestDTO);
                break;

            case "Farming Land":
                property = setFarmingLandDetails(saveFormRequestDTO);
                break;

            case "Open Plot":
                property = setOpenPlotDetails(saveFormRequestDTO);
                break;

            default:
                return ResponseEntity.badRequest().body("Invalid property type");
        }

        setPropetyDetails(saveFormRequestDTO, property);
        propertyDetailsRepository.save(property);
        return new ResponseEntity<>("Form Saved Succesfully", HttpStatus.OK);
    }

    private void setPropetyDetails(SaveFormRequestDTO saveFormRequestDTO, PropertyDetailsEntity property) {
        property.setAddress(saveFormRequestDTO.getAddress());
        property.setCity(saveFormRequestDTO.getCity());
        property.setAgentId(saveFormRequestDTO.getAgentId());
        property.setCountry(saveFormRequestDTO.getCountry());
        property.setLatitude(saveFormRequestDTO.getLatitude());
        property.setLongitude(saveFormRequestDTO.getLongitude());
        property.setPropertSize(saveFormRequestDTO.getPropertySize());
        property.setState(saveFormRequestDTO.getState());
        property.setListingType(saveFormRequestDTO.getListingType());
        property.setPrice(saveFormRequestDTO.getPrice());
        property.setOwnerName(saveFormRequestDTO.getOwnerName());
        property.setPostalCode(saveFormRequestDTO.getPostalCode());
        property.setAreaUnit(saveFormRequestDTO.getAreaUnit());
        property.setRoadAccess(saveFormRequestDTO.isRoadAccess());
        property.setRoadWidth(saveFormRequestDTO.getRoadWidth());
        property.setPropertyFacing(saveFormRequestDTO.getPropertyFacing());
        property.setCreatedBy("ADMIN");
        property.setUpdatedBy("ADMIN");
    }

    private PropertyDetailsEntity setOpenPlotDetails(SaveFormRequestDTO saveFormRequestDTO) {
        PropertyDetailsEntity property;
        OpenPlot plot = new OpenPlot();
        plot.setSoilType(saveFormRequestDTO.getSoilType());
        plot.setWaterSource(saveFormRequestDTO.getWaterSource());
        property = plot;
        return property;
    }

    private PropertyDetailsEntity setFarmingLandDetails(SaveFormRequestDTO saveFormRequestDTO) {
        PropertyDetailsEntity property;
        FarmingLand land = new FarmingLand();
        land.setSoilType(saveFormRequestDTO.getSoilType());
        land.setWaterSource(saveFormRequestDTO.getWaterSource());
        property = land;
        return property;
    }

    private PropertyDetailsEntity setInvidualHouse(SaveFormRequestDTO saveFormRequestDTO) {
        PropertyDetailsEntity property;
        IndividualHouse house = new IndividualHouse();
        house.setNumberOfFloors(saveFormRequestDTO.getNumberOfFloors());
        house.setBuiltupArea(saveFormRequestDTO.getBuiltupArea());
        house.setConstructionYear(saveFormRequestDTO.getConstructionYear());
        property = house;
        return property;
    }

    private PropertyDetailsEntity setFlatDetailsToSave(SaveFormRequestDTO saveFormRequestDTO) {
        PropertyDetailsEntity property;
        Flat flat = new Flat();
        flat.setFlatNumber(saveFormRequestDTO.getFlatNumber());
        flat.setFloorNumber(saveFormRequestDTO.getFloorNumber());
        flat.setTotalFloors(saveFormRequestDTO.getTotalFloors());
        flat.setHasLift(saveFormRequestDTO.getHasLift());
        flat.setMaintenanceFee(saveFormRequestDTO.getMaintenanceFee());
        property = flat;
        return property;
    }

    @Override
    public ResponseEntity<List<PropertyDetailsEntity>> getPropertyDetails() {

        List<PropertyDetailsEntity> propertyDetails = propertyDetailsRepository.findAll();


        return new ResponseEntity<>(propertyDetails,HttpStatus.OK);
    }

    @Override
    public B2FileVersion uploadVideo(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            byte[] fileBytes = file.getBytes();  // Convert MultipartFile to byte[]
            B2ContentSource contentSource = B2ByteArrayContentSource.build(fileBytes);

            B2UploadFileRequest request = B2UploadFileRequest
                    .builder(bucket.getBucketId(),
                            file.getOriginalFilename(),
                            file.getContentType(),
                            contentSource)
                    .build();
            return client.uploadSmallFile(request);
        } catch (B2Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
