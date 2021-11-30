package com.engine.realestatesearchapp.controllers.assemblers;

import com.engine.realestatesearchapp.controllers.requests.RealEstateRequest;
import com.engine.realestatesearchapp.controllers.resources.FileResource;
import com.engine.realestatesearchapp.controllers.resources.RealEstateResource;
import com.engine.realestatesearchapp.repositiories.entities.*;
import com.engine.realestatesearchapp.repositiories.enums.*;
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

    public Plot mapToPlotEntity(RealEstateRequest request) {
        Plot plot = new Plot();
        plot.setType(PlotType.valueOfLabel(request.getPlotType()));
        return plot;
    }

    public Premises mapToPremisesEntity(RealEstateRequest request) {
        Premises premises = new Premises();
        premises.setPurpose(PremisesPurpose.valueOfLabel(request.getPremisesPurpose()));
        premises.setFurnished(request.isFurnished());
        return premises;
    }

    public Flat mapToFlatEntity(RealEstateRequest request) {
        Flat flat = new Flat();
        flat.setRent(request.getRent());
        flat.setType(FlatType.valueOfLabel(request.getFlatType()));
        flat.setFurnished(request.isFurnished());
        flat.setRoomsNumber(request.getRoomsNumber());
        flat.setLevel(request.getFloors());
        return flat;
    }

    public Room mapToRoomEntity(RealEstateRequest request) {
        Room room = new Room();
        room.setType(RoomType.valueOfLabel(request.getRoomType()));
        return room;
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

    public RealEstateResource mapToPlotResource(Plot plot) {
        RealEstateResource resource = mapToResourceWithFiles(plot.getBasicInfo());
        resource.setCategory(RealEstateCategory.PLOTS.getLabel());
        resource.setRealEstateId(plot.getId());
        resource.setPlotType(plot.getType().getLabel());
        return resource;
    }

    public RealEstateResource mapToPremisesResource(Premises premises) {
        RealEstateResource resource = mapToResourceWithFiles(premises.getBasicInfo());
        resource.setCategory(RealEstateCategory.OFFICES_AND_PREMISES.getLabel());
        resource.setRealEstateId(premises.getId());
        resource.setPremisesPurpose(premises.getPurpose().getLabel());
        resource.setFurnished(premises.getFurnished());
        return resource;
    }

    public RealEstateResource mapToFlatResource(Flat flat) {
        RealEstateResource resource = mapToResourceWithFiles(flat.getBasicInfo());
        resource.setCategory(RealEstateCategory.FLATS.getLabel());
        resource.setRealEstateId(flat.getId());
        resource.setRent(flat.getRent());
        resource.setFlatType(flat.getType().getLabel());
        resource.setFurnished(flat.isFurnished());
        resource.setRoomsNumber(flat.getRoomsNumber());
        resource.setFloorNumber(flat.getLevel());
        return resource;
    }

    public RealEstateResource mapToRoomResource(Room room) {
        RealEstateResource resource = mapToResourceWithFiles(room.getBasicInfo());
        resource.setCategory(RealEstateCategory.ROOMS.getLabel());
        resource.setRealEstateId(room.getId());
        resource.setRoomType(room.getType().getLabel());
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
                .realEstateId(entity.getRealEstateId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .size(entity.getSize())
                .sold(entity.isSold())
                .category(entity.getCategory().getLabel())
                .localization(entity.getLocalization())
                .files(entity.getFiles().isEmpty() ? new ArrayList<>() :
                        Collections.singletonList(this.mapToResource(entity.getFiles().get(0))))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private RealEstate mapToRealEstateEntity(RealEstateRequest request) {
        return RealEstate.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .price(request.getPrice())
                .size(request.getSize())
                .category(RealEstateCategory.valueOfLabel(request.getCategory()))
                .offerType(OfferType.valueOfLabel(request.getOfferType()))
                .deleted(false)
                .sold(false)
                .build();
    }

}
