package com.engine.realestatesearchapp.controllers.assemblers;

import com.engine.realestatesearchapp.controllers.requests.RealEstateRequest;
import com.engine.realestatesearchapp.controllers.resources.FileResource;
import com.engine.realestatesearchapp.controllers.resources.RealEstateResource;
import com.engine.realestatesearchapp.repositiories.entities.File;
import com.engine.realestatesearchapp.repositiories.entities.House;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
import com.engine.realestatesearchapp.repositiories.enums.HouseType;
import com.engine.realestatesearchapp.repositiories.enums.RealEstateCategory;
import com.engine.realestatesearchapp.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CommonAssembler {

    private final FileService fileService;

    public FileResource mapToResource(File entity) {
        return FileResource.builder()
                .id(entity.getId())
                .originalFileName(entity.getOriginalFileName())
                .contentType(entity.getContentType())
                .version(entity.getVersion())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public FileResource mapToOfferFileResource(File entity) {
        FileResource resource = mapToResource(entity);
        resource.setBytes(fileService.getFileBytes(entity.getPath()));
        return resource;
    }

    public FileResource mapToAvatarFileResource(File entity) {
        FileResource resource = mapToResource(entity);
        resource.setBytes(fileService.getAvatarBytes(entity.getPath()));
        return resource;
    }

    public House mapToHouseEntity(RealEstateRequest request) {
        House house = new House();
        house.setPlotSize(request.getPlotSize());
        house.setFurnished(request.isFurnished());
        house.setType(HouseType.valueOfLabel(request.getHouseType()));
        house.setRoomsNumber(request.getRoomsNumber());
        house.setFloorsNumber(request.getRoomsNumber());
        return house;
    }

    public RealEstate mapToEntity(RealEstateRequest request) {
        RealEstate entity = mapToRealEstateEntity(request);
        entity.setFiles(new ArrayList<>());
        return entity;
    }

    public RealEstateResource mapToResourceWithFiles(RealEstate entity) {
        RealEstateResource resource = mapToResource(entity);
        resource.setFiles(entity.getFiles().isEmpty() ? new ArrayList<>() :
                entity.getFiles().stream().map(this::mapToOfferFileResource).collect(Collectors.toList()));
        return resource;
    }

    public RealEstateResource mapToHouseResource(House house) {
        RealEstateResource resource = mapToResourceWithFiles(house.getBasicInfo());
        resource.setCategory(RealEstateCategory.HOUSES.getLabel());
        resource.setRealEstateId(house.getId());
        resource.setPlotSize(house.getPlotSize());
        resource.setFurnished(house.getFurnished());
        resource.setHouseType(house.getType().getLabel());
        resource.setRoomsNumber(house.getRoomsNumber());
        resource.setFloorsNumber(house.getRoomsNumber());
        return resource;
    }

    public RealEstateResource mapToResourceWithAvatar(RealEstate entity) {
        RealEstateResource resource = mapToResource(entity);
        resource.setFiles(entity.getFiles().isEmpty() ? new ArrayList<>() :
                Collections.singletonList(this.mapToAvatarFileResource(entity.getFiles().get(0))));
        return resource;
    }

    private RealEstateResource mapToResource(RealEstate entity) {
        return RealEstateResource.builder()
                .basicInfoId(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .size(entity.getSize())
                .sold(entity.isSold())
                .localization(entity.getLocalization())
                .files(entity.getFiles().isEmpty() ? new ArrayList<>() :
                        Collections.singletonList(this.mapToResource(entity.getFiles().get(0))))
                .build();
    }

    private RealEstate mapToRealEstateEntity(RealEstateRequest request) {
        return RealEstate.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .size(request.getSize())
                .deleted(false)
                .sold(false)
                .build();
    }

}
