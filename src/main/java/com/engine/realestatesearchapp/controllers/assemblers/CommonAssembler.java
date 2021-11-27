package com.engine.realestatesearchapp.controllers.assemblers;

import com.engine.realestatesearchapp.controllers.requests.RealEstateRequest;
import com.engine.realestatesearchapp.controllers.resources.FileResource;
import com.engine.realestatesearchapp.controllers.resources.RealEstateResource;
import com.engine.realestatesearchapp.repositiories.entities.File;
import com.engine.realestatesearchapp.repositiories.entities.House;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
import com.engine.realestatesearchapp.repositiories.entities.RealEstateTypes;
import com.engine.realestatesearchapp.repositiories.enums.FlatType;
import com.engine.realestatesearchapp.repositiories.enums.HouseType;
import com.engine.realestatesearchapp.repositiories.enums.OfferType;
import com.engine.realestatesearchapp.repositiories.enums.PlotType;
import com.engine.realestatesearchapp.repositiories.enums.PremisesPurpose;
import com.engine.realestatesearchapp.repositiories.enums.RealEstateCategory;
import com.engine.realestatesearchapp.repositiories.enums.RoomType;
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
        house.setTitle(request.getTitle());
        house.setDescription(request.getDescription());
        house.setCategory(RealEstateCategory.HOUSES);
        house.setPrice(request.getPrice());
        house.setSize(request.getSize());
        house.setDeleted(false);
        house.setPlotSize(request.getPlotSize());
        house.setFurnished(request.isFurnished());
        house.setType(HouseType.valueOfLabel(request.getHouseType()));
        house.setRoomsNumber(request.getRoomsNumber());
        house.setFloorsNumber(request.getRoomsNumber());
        return house;
    }

    public RealEstateResource mapToHouseResource(House entity) {
        RealEstateResource resource = new RealEstateResource();
        resource.setTitle(entity.getTitle());
        resource.setDescription(entity.getDescription());
        resource.setCategory(RealEstateCategory.HOUSES.getLabel());
        resource.setPrice(entity.getPrice());
        resource.setSize(entity.getSize());
        resource.setPlotSize(entity.getPlotSize());
        resource.setFurnished(entity.getFurnished());
        resource.setHouseType(entity.getType().getLabel());
        resource.setRoomsNumber(entity.getRoomsNumber());
        resource.setFloorsNumber(entity.getRoomsNumber());
        return resource;
    }

    public RealEstate mapToEntity(RealEstateRequest request) {
        RealEstateTypes types = mapToRealEstateTypes(request);
        RealEstate entity = mapToRealEstateEntity(request);
        entity.setTypes(types);
        entity.setFiles(new ArrayList<>());
        return entity;
    }

    public RealEstateResource mapToResourceWithFiles(RealEstate entity) {
        RealEstateResource resource = mapToResource(entity);
        resource.setFiles(entity.getFiles().isEmpty() ? new ArrayList<>() :
                entity.getFiles().stream().map(this::mapToOfferFileResource).collect(Collectors.toList()));
        return resource;
    }

    public RealEstateResource mapToResourceWithAvatar(RealEstate entity) {
        RealEstateResource resource = mapToResource(entity);
        resource.setFiles(entity.getFiles().isEmpty() ? new ArrayList<>() :
                Collections.singletonList(this.mapToAvatarFileResource(entity.getFiles().get(0))));
        return resource;
    }

    private RealEstateResource mapToResource(RealEstate entity) {
        RealEstateTypes types = entity.getTypes();
        return RealEstateResource.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .rent(entity.getRent())
                .size(entity.getSize())
                .roomsNumber(entity.getRoomsNumber())
                .floorsNumber(entity.getFloors())
                .furnished(entity.getFurnished())
                .sold(entity.isSold())
                .localization(entity.getLocalization())
                .category(types.getCategory() != null ? types.getCategory().getLabel() : null)
                .offerType(types.getOfferType() != null ? types.getOfferType().getLabel() : null)
                .houseType(types.getHouseType() != null ? types.getHouseType().getLabel() : null)
                .roomType(types.getRoomType() != null ? types.getRoomType().getLabel() : null)
                .plotType(types.getPlotType() != null ? types.getPlotType().getLabel() : null)
                .flatType(types.getFlatType() != null ? types.getFlatType().getLabel() : null)
                .premisesPurpose(types.getPremisesPurpose() != null ? types.getPremisesPurpose().getLabel() : null)
                .files(entity.getFiles().isEmpty() ? new ArrayList<>() :
                        Collections.singletonList(this.mapToResource(entity.getFiles().get(0))))
                .build();
    }

    private RealEstate mapToRealEstateEntity(RealEstateRequest request) {
        return RealEstate.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .rent(request.getRent())
                .size(request.getSize())
                .roomsNumber(request.getRoomsNumber())
                .plotSize(request.getPlotSize())
                .floors(request.getFloors())
                .furnished(request.isFurnished())
                .deleted(false)
                .sold(false)
                .build();
    }

    private RealEstateTypes mapToRealEstateTypes(RealEstateRequest request) {
        return RealEstateTypes.builder()
                .category(RealEstateCategory.valueOfLabel(request.getCategory()))
                .offerType(OfferType.valueOfLabel(request.getOfferType()))
                .houseType(HouseType.valueOfLabel(request.getHouseType()))
                .roomType(RoomType.valueOfLabel(request.getRoomType()))
                .plotType(PlotType.valueOfLabel(request.getPlotType()))
                .flatType(FlatType.valueOfLabel(request.getFlatType()))
                .premisesPurpose(PremisesPurpose.valueOfLabel(request.getPremisesPurpose()))
                .build();
    }
}
