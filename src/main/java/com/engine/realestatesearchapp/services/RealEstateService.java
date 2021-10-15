package com.engine.realestatesearchapp.services;

import com.engine.realestatesearchapp.controllers.requests.RealEstateRequest;
import com.engine.realestatesearchapp.repositiories.RealEstateRepository;
import com.engine.realestatesearchapp.repositiories.entities.Flat;
import com.engine.realestatesearchapp.repositiories.entities.House;
import com.engine.realestatesearchapp.repositiories.entities.Plot;
import com.engine.realestatesearchapp.repositiories.entities.Premises;
import com.engine.realestatesearchapp.repositiories.entities.RealEstate;
import com.engine.realestatesearchapp.repositiories.entities.Room;
import com.engine.realestatesearchapp.repositiories.enums.RealEstateCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RealEstateService {

    private final RealEstateRepository realEstateRepository;

    public RealEstate createRealEstate(RealEstateRequest request) {
        RealEstate realEstate = RealEstate.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .size(request.getSize())
                .deleted(false)
                .sold(false)
                .offerType(request.getOfferType())
                .build();
        switch (request.getCategory()) {
            case FLATS:
                Flat flat = Flat.builder()
                        .rent(request.getRent())
                        .type(request.getFlatType())
                        .furnished(request.isFurnished())
                        .level(request.getLevel())
                        .roomsNumber(request.getRoomsNumber())
                        .build();
                break;
            case HOUSES:
                House house = House.builder()
                        .type(request.getHouseType())
                        .plotSize(request.getPlotSize())
                        .furnished(request.isFurnished())
                        .roomsNumber(request.getRoomsNumber())
                        .floorsNumber(request.getFloorsNumber())
                        .build();
                break;
            case PLOTS:
                Plot plot = Plot.builder()
                        .type(request.getPlotType())
                        .build();
                break;
            case OFFICES_AND_PREMISES:
                Premises premises = Premises.builder()
                        .furnished(request.isFurnished())
                        .purpose(request.getPremisesPurpose())
                        .build();
                break;
            case ROOMS:
                Room room = Room.builder()
                        .type(request.getRoomType())
                        .build();
                break;
        }
        return null;
        //return realEstateRepository.save(realEstate);
    }
}
