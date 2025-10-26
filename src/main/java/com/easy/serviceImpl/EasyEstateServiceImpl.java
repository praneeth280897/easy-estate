package com.easy.serviceImpl;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.contentSources.B2ByteArrayContentSource;
import com.backblaze.b2.client.contentSources.B2ContentSource;
import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.client.structures.*;
import com.easy.component.EasyEstateComponent;
import com.easy.entity.PropertyDetailsEntity;
import com.easy.entity.PropertyTypeEntity;
import com.easy.repository.PropertyDetailsRepository;
import com.easy.repository.PropertyTypeRepository;
import com.easy.request.SaveFormRequestDTO;
import com.easy.response.PropertyResponseDTO;
import com.easy.response.PropertyTypeRequiredResponse;
import com.easy.service.EasyEstateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class EasyEstateServiceImpl implements EasyEstateService {

    @Autowired
    private PropertyDetailsRepository propertyDetailsRepository;

    @Autowired
    private B2StorageClient client;

    @Autowired
    private B2Bucket bucket;

    @Value("${backblaze.bucketName}")
    private String bucketName;

    @Value("${backblaze.accountId}")
    private String accountId;

    @Value("${backblaze.applicationKey}")
    private String applicationKey;

    @Autowired
    private PropertyTypeRepository propertyTypeRepository;

    @Autowired
    private EasyEstateComponent easyEstateComponent;

    @Override
    public ResponseEntity<String> saveForm(SaveFormRequestDTO saveFormRequestDTO) throws B2Exception {
        PropertyDetailsEntity property = new PropertyDetailsEntity();

        switch (saveFormRequestDTO.getPropertyType()) {
            case "Flat":
                property = setFlatDetailsToSave(saveFormRequestDTO, property);
                break;

            case "Individual House":
                property = setInvidualHouse(saveFormRequestDTO, property);
                break;

            case "Farming Land":
                property = setFarmingLandDetails(saveFormRequestDTO, property);
                break;

            case "Open Plot":
                property = setOpenPlotDetails(saveFormRequestDTO, property);
                break;

            default:
                return ResponseEntity.badRequest().body("Invalid property type");
        }

        setPropetyDetails(saveFormRequestDTO, property);
        propertyDetailsRepository.save(property);
        for (String fileData : saveFormRequestDTO.getFileData()) {
            String fileType = fileData.substring(fileData.indexOf(":") + 1, fileData.indexOf(";"));
            byte[] bytes = Base64.getDecoder().decode(fileData.split(",")[1].replaceAll("\\s+", ""));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            String timestamp = LocalDateTime.now().format(formatter);
            String fileName = property.getId() + "/" + timestamp;
            uploadFileToBlob(fileType, bytes, fileName);
        }

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
        property.setPropertyType(saveFormRequestDTO.getPropertyType());
        property.setCreatedBy("ADMIN");
        property.setUpdatedBy("ADMIN");
    }

    private PropertyDetailsEntity setOpenPlotDetails(SaveFormRequestDTO saveFormRequestDTO, PropertyDetailsEntity property) {

        property.setSoilType(saveFormRequestDTO.getSoilType());
        property.setWaterSource(saveFormRequestDTO.getWaterSource());
        return property;
    }

    private PropertyDetailsEntity setFarmingLandDetails(SaveFormRequestDTO saveFormRequestDTO, PropertyDetailsEntity property) {

        property.setSoilType(saveFormRequestDTO.getSoilType());
        property.setWaterSource(saveFormRequestDTO.getWaterSource());
        return property;
    }

    private PropertyDetailsEntity setInvidualHouse(SaveFormRequestDTO saveFormRequestDTO, PropertyDetailsEntity property) {
        property.setNumberOfFloors(saveFormRequestDTO.getNumberOfFloors());
        property.setBuiltupArea(saveFormRequestDTO.getBuiltupArea());
        property.setConstructionYear(saveFormRequestDTO.getConstructionYear());
        return property;
    }

    private PropertyDetailsEntity setFlatDetailsToSave(SaveFormRequestDTO saveFormRequestDTO, PropertyDetailsEntity property) {

        property.setFlatNumber(saveFormRequestDTO.getFlatNumber());
        property.setFloorNumber(saveFormRequestDTO.getFloorNumber());
        property.setTotalFloors(saveFormRequestDTO.getTotalFloors());
        property.setHasLift(saveFormRequestDTO.getHasLift());
        property.setMaintenanceFee(saveFormRequestDTO.getMaintenanceFee());
        return property;
    }

    @Override
    public ResponseEntity<List<PropertyResponseDTO>> getPropertyDetails() {

        List<PropertyDetailsEntity> propertyDetails = propertyDetailsRepository.findAll();
        List<PropertyResponseDTO> response = new ArrayList<>();
        for (PropertyDetailsEntity priceDEtailsEntity : propertyDetails) {
            response.add(setResponseData(priceDEtailsEntity));
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private PropertyResponseDTO setResponseData(PropertyDetailsEntity priceDEtailsEntity) {
        PropertyResponseDTO dto = new PropertyResponseDTO();
        dto.setPropertySize(priceDEtailsEntity.getPropertSize());
        dto.setAreaUnit(priceDEtailsEntity.getAreaUnit());
        dto.setListingType(priceDEtailsEntity.getListingType());
        dto.setPrice(priceDEtailsEntity.getPrice());
        dto.setCityName(priceDEtailsEntity.getCity());
        dto.setStateName(priceDEtailsEntity.getState());
        dto.setCountryName(priceDEtailsEntity.getCountry());
        dto.setAddress(priceDEtailsEntity.getAddress());
        dto.setPostalCode(priceDEtailsEntity.getPostalCode());
        dto.setLatitude(priceDEtailsEntity.getLatitude());
        dto.setLongitude(priceDEtailsEntity.getLongitude());
        dto.setAgentId(priceDEtailsEntity.getAgentId());
        dto.setOwnerName(priceDEtailsEntity.getOwnerName());
        dto.setRoadAccess(priceDEtailsEntity.isRoadAccess());
        dto.setRoadWidth(priceDEtailsEntity.getRoadWidth());
        dto.setPropertyType(priceDEtailsEntity.getPropertyType());
        dto.setFlatNumber(priceDEtailsEntity.getFlatNumber());
        dto.setFloorNumber(priceDEtailsEntity.getFloorNumber());
        dto.setTotalFloors(priceDEtailsEntity.getTotalFloors());
        dto.setHasLift(priceDEtailsEntity.getHasLift());
        dto.setMaintenanceFee(priceDEtailsEntity.getMaintenanceFee());

        dto.setNumberOfFloors(priceDEtailsEntity.getNumberOfFloors());
        dto.setBuiltupArea(priceDEtailsEntity.getBuiltupArea());
        dto.setConstructionYear(priceDEtailsEntity.getConstructionYear());

        dto.setSoilType(priceDEtailsEntity.getSoilType());
        dto.setWaterSource(priceDEtailsEntity.getWaterSource());
        dto.setUrl(getFileToOpen(priceDEtailsEntity.getId().toString()));
        return dto;
    }

    @Override
    public String uploadVideo(MultipartFile file, Long propertyId) throws IOException, B2Exception {

        String fileName = propertyId.toString() + "/" + file.getOriginalFilename();
        byte[] fileBytes = file.getBytes();
        uploadFileToBlob(file.getContentType(), fileBytes, fileName);
        return String.format("File Uploaded Successfully : ", fileName);
    }

    private void uploadFileToBlob(String fileType, byte[] fileBytes, String fileName) throws B2Exception {
        B2ContentSource contentSource = B2ByteArrayContentSource.build(fileBytes);
        B2UploadFileRequest request = B2UploadFileRequest
                .builder(bucket.getBucketId(),
                        fileName,
                        fileType,
                        contentSource)
                .build();

        client.uploadSmallFile(request);
    }

    @Override
    public List<String> getFileToOpen(String propertyId) {
        List<String> secureUrls = new ArrayList<>();
        try {
            B2ListFileVersionsRequest.Builder request = B2ListFileVersionsRequest
                    .builder(bucket.getBucketId());

            if (!propertyId.endsWith("/")) {
                propertyId += "/";
            }
            request.setPrefix(propertyId);
            String downloadUrl = client.getAccountAuthorization().getDownloadUrl();
            int validSeconds = (int) TimeUnit.HOURS.toSeconds(1);
            B2GetDownloadAuthorizationRequest authRequest = B2GetDownloadAuthorizationRequest
                    .builder(bucket.getBucketId(), propertyId, validSeconds)
                    .build();

            B2DownloadAuthorization auth = client.getDownloadAuthorization(authRequest);

            for (B2FileVersion fileVersion : client.fileVersions(request.build())) {
                String fileName = fileVersion.getFileName();


                String secureUrl = String.format(
                        "%s/file/%s/%s?Authorization=%s",
                        downloadUrl,
                        bucket.getBucketName(),
                        fileName,
                        auth.getAuthorizationToken()
                );

                secureUrls.add(secureUrl);
            }

        } catch (B2Exception e) {
            log.error("Failed to list or authorize files in folder '{}': {}", propertyId, e.getMessage(), e);
        }

        return secureUrls;

    }


    @Override
    public PropertyResponseDTO getPropertyDetailsById(Long propertyId) {

        Optional<PropertyDetailsEntity> propertyDetailsEntity = propertyDetailsRepository.findById(propertyId);
        if (propertyDetailsEntity.isPresent()) {
            PropertyResponseDTO responseDTO = setResponseData(propertyDetailsEntity.get());
            return responseDTO;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Given PropertyId doesnot Exists");
        }
    }

    @Override
    public ResponseEntity<PropertyTypeRequiredResponse> getRequireFieldsByPropertyId(Long propertyId) throws Exception {
        Optional<PropertyTypeEntity> propertyType = propertyTypeRepository.findById(propertyId);
        if (propertyType.isPresent()) {
            return new ResponseEntity<>(easyEstateComponent.getRequireFieldsByPropertyId(propertyType.get()), HttpStatus.OK);
        }
        throw new Exception("Property Type not Present" + propertyId);
    }
}
