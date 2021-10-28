package com.engine.realestatesearchapp.controllers.assemblers;

import com.engine.realestatesearchapp.controllers.requests.RealEstateRequest;
import com.engine.realestatesearchapp.controllers.resources.FileResource;
import com.engine.realestatesearchapp.controllers.resources.RealEstateResource;
import com.engine.realestatesearchapp.repositiories.entities.File;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
import com.engine.realestatesearchapp.repositiories.entities.RealEstateTypes;
import com.engine.realestatesearchapp.repositiories.enums.FlatType;
import com.engine.realestatesearchapp.repositiories.enums.HouseType;
import com.engine.realestatesearchapp.repositiories.enums.OfferType;
import com.engine.realestatesearchapp.repositiories.enums.PlotType;
import com.engine.realestatesearchapp.repositiories.enums.PremisesPurpose;
import com.engine.realestatesearchapp.repositiories.enums.RealEstateCategory;
import com.engine.realestatesearchapp.repositiories.enums.RoomType;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CommonAssembler {

    public static FileResource mapToResource(File entity) {
        return FileResource.builder()
                .id(entity.getId())
                .originalFileName(entity.getOriginalFileName())
                .contentType(entity.getContentType())
                .version(entity.getVersion())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public static RealEstate mapToEntity(RealEstateRequest request) {
        RealEstateTypes types = mapToRealEstateTypes(request);
        RealEstate entity = mapToRealEstateEntity(request);
        entity.setTypes(types);
        entity.setFiles(new ArrayList<>());
        return entity;
    }

    public static RealEstateResource mapToResource(RealEstate entity) {
        RealEstateTypes types = entity.getTypes();
        return RealEstateResource.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .rent(entity.getRent())
                .size(entity.getSize())
                .roomsNumber(entity.getRoomsNumber())
                .floors(entity.getFloors())
                .furnished(entity.getFurnished())
                .sold(entity.isSold())
                .category(types.getCategory() != null ? types.getCategory().getLabel() : null)
                .offerType(types.getOfferType() != null ? types.getOfferType().getLabel() : null)
                .houseType(types.getHouseType() != null ? types.getHouseType().getLabel() : null)
                .roomType(types.getRoomType() != null ? types.getRoomType().getLabel() : null)
                .plotType(types.getPlotType() != null ? types.getPlotType().getLabel() : null)
                .flatType(types.getFlatType() != null ? types.getFlatType().getLabel() : null)
                .premisesPurpose(types.getPremisesPurpose() != null ? types.getPremisesPurpose().getLabel() : null)
                .files(entity.getFiles().stream().map(CommonAssembler::mapToResource).collect(Collectors.toList()))
                .build();
    }

    private static RealEstate mapToRealEstateEntity(RealEstateRequest request) {
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

    private static RealEstateTypes mapToRealEstateTypes(RealEstateRequest request) {
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
